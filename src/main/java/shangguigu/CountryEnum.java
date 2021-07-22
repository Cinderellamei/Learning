package shangguigu;

import lombok.Getter;

/**
 *
 */
public enum CountryEnum {
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");


    @Getter private Integer reCode;
    @Getter private String reMessage;

    CountryEnum(Integer reCode,String reMessage) {
        this.reCode = reCode;
        this.reMessage = reMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index) {
        CountryEnum [] myArray = CountryEnum.values();
        for(CountryEnum element:myArray) {
            if(index == element.getReCode()) {
                return element;
            }
        }
        return null;
    }
}
