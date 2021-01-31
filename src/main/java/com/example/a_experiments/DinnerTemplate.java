package com.example.a_experiments;

public abstract class DinnerTemplate {

    private void tuangou() {
        System.out.println("团购");
    }

    abstract void haveDinner();

    private void pingjia() {

        System.out.println("评价");
    }

    public void process() {
        tuangou();
        haveDinner();
        pingjia();
    }

}

class WesternStyleFood extends DinnerTemplate {

    @Override
    protected void haveDinner() {
        System.out.println("吃西餐");
    }

}

class ChineseStyleFood extends DinnerTemplate {

    @Override
    protected void haveDinner() {
        System.out.println("吃中餐");
    }

}