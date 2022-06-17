package com.xjt.javase.designPattern.template;

public abstract class Soyamilk {
    //模板方法 make 可以做成final类型，不让子类去覆盖它
    final void make() throws InterruptedException {
        //可以添加计时
        long start = System.currentTimeMillis();
        select();

        if(isAddCondiments()){
            addCondiments();
        }

        soak();
        beat();
        long end = System.currentTimeMillis();
        System.out.println("豆浆的制作时间是："+(end - start));
    }

    //1、选材
    void select() {
        System.out.println("步骤1：选择新鲜的黄豆 ");
    }

    //2、添加配料（由于要制作不能口味的豆浆 所以加配料要在子类中实现）
    abstract void addCondiments();

    //3、浸泡(加入不同的配料也需要浸泡不同的时间)
    abstract void soak();

    //4、放入豆浆机中打碎
    void beat() throws InterruptedException {
        System.out.println("黄豆和配料放入豆浆机中打碎 耗时2s。。。");
        Thread.sleep(2000);
    }

    //定义一个钩子，决定是否需要添加配料（子类可以重写也可以不重写）
    boolean isAddCondiments(){
        return true;
    }
}
