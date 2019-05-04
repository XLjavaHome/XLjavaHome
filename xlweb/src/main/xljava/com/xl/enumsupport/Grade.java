package com.xl.enumsupport;

public enum Grade {
    // 1.
    // A,B,C,D,E;;
    // //因为加了有参的构造函数，所以上面报错。
    // private Grade(){
    //
    // }
    // 2.如何定义枚举的构造函数、方法和字段，去封装更多的信息
    // A("100-90"),B("89-80"),C("70-79"),D("60-69"),E("0-59");
    // 3.带抽象方法的枚举.希望调用就得到对应的等级（优、良..)
    A("100-90") {
        @Override
        public String localeValue() {
            return "优";
        }
    }, B("89-80") {
        @Override
        public String localeValue() {
            return "良好";
        }
    }, C("70-79") {
        @Override
        public String localeValue() {
            return "一般";
        }
    }, D("60-69") {
        @Override
        public String localeValue() {
            return "差";
        }
    }, E("0-59") {
        @Override
        public String localeValue() {
            return "不及格";
        }
    };
    // 希望拿到对象就可以对应的分数
    private String value;

    Grade(String value) {
        this.value = value;
    }

    public abstract String localeValue();

    public String getValue() {
        return this.value;
    }
}
