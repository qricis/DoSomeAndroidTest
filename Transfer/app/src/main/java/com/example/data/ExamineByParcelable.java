package com.example.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ExamineByParcelable implements Parcelable {

    private String name;
    private int id;
    private int number;
    private String type;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Test2{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", number=" + number +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        //描述内容，保持原来的return 0即可
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //把要传递的数据写入
        dest.writeString(name);
        dest.writeString(type);
        dest.writeInt(id);
        dest.writeInt(number);
    }

    //对象的创建器
    public static final Parcelable.Creator<ExamineByParcelable> CREATOR
            = new Parcelable.Creator<ExamineByParcelable>() {
        public ExamineByParcelable createFromParcel(Parcel in) {
            ExamineByParcelable exam =  new ExamineByParcelable() ;
            exam.name = in.readString();
            exam.type = in.readString();
            exam.id = in.readInt();
            exam.number = in.readInt();
            return exam;
            //return new exam(in);
        }

        public ExamineByParcelable[] newArray(int size) {
            return new ExamineByParcelable[size];
        }
    };

}
