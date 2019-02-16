package main.java.weather.UIApp.label;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class LabelObjList {

    List<LabelObj> labelObjList;
    LabelObj labelObj1;
    LabelObj labelObj2;
    LabelObj labelObj3;
    LabelObj labelObj4;
    LabelObj labelObj5;


    public LabelObjList(Label dateLblDay1, ImageView weatherIconDay1, Label temperatureLblDay1, Label dateLblDay2, ImageView weatherIconDay2,
                        Label temperatureLblDay2, Label dateLblDay3, ImageView weatherIconDay3, Label temperatureLblDay3, Label dateLblDay4,
                        ImageView weatherIconDay4, Label temperatureLblDay4, Label dateLblDay5, ImageView weatherIconDay5, Label temperatureLblDay5) {
        labelObj1 = new LabelObj(dateLblDay1, weatherIconDay1, temperatureLblDay1);
        labelObj2 = new LabelObj(dateLblDay2, weatherIconDay2, temperatureLblDay2);
        labelObj3 = new LabelObj(dateLblDay3, weatherIconDay3, temperatureLblDay3);
        labelObj4 = new LabelObj(dateLblDay4, weatherIconDay4, temperatureLblDay4);
        labelObj5 = new LabelObj(dateLblDay5, weatherIconDay5, temperatureLblDay5);
        labelObjList = new ArrayList<>();
        labelObjList.add(labelObj1);
        labelObjList.add(labelObj2);
        labelObjList.add(labelObj3);
        labelObjList.add(labelObj4);
        labelObjList.add(labelObj5);
    }

    public List<LabelObj> getLabelObjList() {
        return labelObjList;
    }

    public LabelObj getLabelObj1() {
        return labelObj1;
    }

    public LabelObj getLabelObj2() {
        return labelObj2;
    }

    public LabelObj getLabelObj3() {
        return labelObj3;
    }

    public LabelObj getLabelObj4() {
        return labelObj4;
    }

    public LabelObj getLabelObj5() {
        return labelObj5;
    }
}
