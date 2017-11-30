# Dagger2 的总结
> Dagger2 是基于 JSR-330（ Java Spencification Requests ） 标准的依赖注入框架，编译期间自动生成代码，负责依赖对象的创建，遵循 依赖倒置原则

依赖注入：不在类中实例化其他依赖的类，而是把你依赖的类实例化了，然后以参数的方式传入构造函数中

#### @Inject
1. 构造器依赖
- 告诉 Dagger2 可以使用这个构造器构建对象
```
public class Grass implements Flower {
    @Inject
    public Grass(){
    }

    @Override
    public String say() {
        return "我是草";
    }
}
```
   - 注入构造器所需的依赖
```
 public class User {
    Flower flower;

    public User(Flower flower) {
        this.flower = flower;
    }

    public String show() {
        return flower.say();
    }
}
```
2. 属性注入
3. 方法注入

#### @Component
void inject(Activity activity)，Dagger2 从目标类开始查找 inject 注解，自动生成依赖注入的代码，调用 inject 可完成依赖的注入
```
@Component
public interface ActivityComponent {
    void inject(MainActivity activity);
}
```
#### @Module 配合 @Provides 使用
如果需要对第三方库进行依赖注入，或者一个类中有多个构方法（@Inject 只能使用一个），这时候就需要用到 Module 了。

Module 告诉 Component 到这里获取以来对象，提供依赖。

@Component 可以指定多个 @Module ，也可以依赖其他 Component（后面举例）。

#### @Qualifier 和 @Named
@Named 是基于 String 的限定符，但是因为是 String 类型，所以容易弄错，所以需要用 @Qualifier 来自定义限定符。

当有多个依赖（多个 @Provides 修饰）可提供时，可通过限定符标注，提供相应的依赖。
```
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface GrassQualifier {
}
```
```
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface RoseQualifier {
}
```
```
@Module
public class FlowerModule {

    @RoseQualifier
    @Provides
    Flower provideRose() {
        return new Rose();
    }

    @GrassQualifier
    @Provides
    Flower provideGrass() {
        return new Grass();
    }
}
```

#### @Component 的 dependencies 和 @SubComponent
Component 可以依赖其他的 Component，获取其他 Component 的依赖

1. dependencies 可以知道依赖谁，可单独注入，明确所依赖
```
@Component(modules = FlowerModule.class)
public interface FlowerComponent {
    @RoseQualifier
    Flower getRose();

    @GrassQualifier
    Flower getGrass();
}
```
```
@Component(modules = UserModule.class, dependencies = FlowerComponent.class)
public interface UserComponent {
    User getUser();
}
```
```
@Component(dependencies = UserComponent.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
}
```
Activity 中调用
```
DaggerActivityComponent.builder()
                .userComponent(DaggerUserComponent.builder()
                .flowerComponent(DaggerFlowerComponent.create()).build())
                .build()
                .inject(this);
```
2. SubComponent 不知道依赖谁，组件 Component 紧密联系，只关心 Component，SubComponent 可通过 Component.xxx 调用
```
@Component(modules = PeopleModule.class)
public interface PeopleComponent {

    UserModelComponent plus(UserModelModule userModelModule);
}
```

```
@Subcomponent(modules = UserModelModule.class)
public interface UserModelComponent {

    ActivityComponent plus();
}
```

```
@Subcomponent
public interface ActivityComponent {

    void inject(MainActivity activity);
}
```

```
DaggerPeopleComponent.builder().build()
                .plus(new UserModelModule())
                .plus().inject(this);
```

#### @Scope 和 @Singleton
- @Scope 是用来管理依赖的生命周期，类似 @Qualifier 用来自定义注解，而 Singleton 则是 Scope 的默认实现
- @Singleton 是添加在 @Component 和 @Provides ，如果有子 Component，也需要添加 @Scope 修饰
- @Scope 保证在 @Component 中是唯一的"**局部单例**",@Provides 方法提供的依赖将会在 Component 中保持"**局部单例**"，若 Component 中标注了 @Scope，@Provides 没有标注，那么这个 @Scope 也不会起作用，而 Component 的 Scope 的使用是为了能顺利通过编译
- 多个 activity 都重新 build 了一个 Component（每个 activity 都 DaggerUserComponent 创建了 Component ），所以"**单例**"对象的地址也变了，没法做到全局"**单例**"
> 如何保证 **全局单例** 呢
1. 依赖在 Component 中是单例，相同的自定义 @Scope
2. 对应的 Component 在 App 中只初始化一次，每次注入依赖都使用这个 Component 对象

代码地址：
[github](https://github.com/liudf731098327/DaggerSample)

参考：
[Dagger2 最清晰的使用教程](http://www.jianshu.com/p/24af4c102f62)
[使用Dagger2前你必须了解的一些设计原则](http://www.jianshu.com/p/cc1427e385b5)
