package com.example.admin.fashion;

import java.util.TreeSet;

/**
 * Created by admin on 2016/08/03.
 */
public class TrainTime  {

    //http://www.jreast-timetable.jp/1608/timetable/tt0866/0866060.html
    //中央線快速時刻表（新宿から八王子方面）
    //新宿→東小金井駅の所要時間
    int traintime = 21;
    //東小金井駅→学校までの所要時間
    int walktime = 15;
    //何分前に学校に着くか
    int waittime = 15;

    int toSinjuku = 40;

    /*1限の電車時間候補
    int one[] = {24,26,28,32,34,36,38,40,42,44};
    int two[] = {1,5,7,10,12,16,25,31,39,47};
    int three[]={1,3,12,17,24,31,39,46,54};
    int four[]={2,9,12,17,24,31,39,46,54};
    int five[]={2,9,12,15,24,31,39,47,54};*/

    public TrainTime(){

    }

    public String text(int input){
        //inputは○限目をとってくる
        switch (input){
            case 1:
                if(waittime == 10){
                    //8:44
                    int hour = 8;
                    int min = 44;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 15){
                    //8:38
                    int hour = 8;
                    int min = 38;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 20){
                    //8:34
                    int hour = 8;
                    int min = 34;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 25){
                    //8:28
                    int hour = 8;
                    int min = 28;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 30){
                    //8:38
                    int hour = 8;
                    int min = 24;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else{
                    return "error";
                }
            case 2:
                if(waittime == 10){
                    //10:16
                    int hour = 10;
                    int min = 16;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 15){
                    //10:16
                    int hour = 10;
                    int min = 16;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 20){
                    //10:12
                    int hour =10;
                    int min = 12;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 25){
                    //10:7
                    int hour =10;
                    int min = 7;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 30){
                    //10:1
                    int hour =10;
                    int min = 1;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else{
                    return "error";
                }
            case 3:
                if(waittime == 10){
                    //12:39
                    int hour =12;
                    int min = 39;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 15){
                    //12:39
                    int hour =12;
                    int min = 39;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 20){
                    //12:31
                    int hour =12;
                    int min = 31;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 25){
                    //12:24
                    int hour =12;
                    int min = 24;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 30){
                    //12:24
                    int hour =12;
                    int min = 24;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else{
                    return "error";
                }
            case 4:
                if(waittime == 10){
                    //14:24
                    int hour =14;
                    int min = 24;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 15){
                    //14:17
                    int hour =14;
                    int min = 17;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 20){
                    //14:12
                    int hour =14;
                    int min = 12;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 25){
                    //14:9
                    int hour =14;
                    int min = 9;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 30){
                    //14:2
                    int hour =14;
                    int min = 2;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else{
                    return "error";
                }
            case 5:
                if(waittime == 10){
                    //16:2
                    int hour = 16;
                    int min = 2;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 15){
                    //15:54
                    int hour =15;
                    int min = 54;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 20){
                    //15:54
                    int hour =15;
                    int min = 54;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 25){
                    //15:47
                    int hour =15;
                    int min = 47;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else if(waittime == 30){
                    //15:39
                    int hour =15;
                    int min = 39;
                    int sub = min - toSinjuku;
                    if(sub<0){
                        int min2 = sub + 60;
                        int hour2 = hour-1;
                        return Integer.toString(hour2)+":"+Integer.toString(min2);
                    }else{
                        return Integer.toString(hour)+":"+Integer.toString(sub);
                    }
                }else{
                    return "error";
                }
        }
        return "あれ？";
    }


}

 /*     時刻メモ
        int timetable[][] = new int[15][9];
        timetable[8][0]=24;
        timetable[8][1] =26;
        timetable[8][2]=28;
        timetable[8][3]=32;
        timetable[8][4]=34;
        timetable[8][5]=36;
        timetable[8][6]=38;
        timetable[8][7]=40;
        timetable[8][8]=42;
        timetable[8][9]=44;
        timetable[8][10]=46;
        timetable[8][11]=48;
        timetable[8][12]=50;
        timetable[8][13]=52;
        timetable[8][14]=54;
        timetable[8][15]=56;
        timetable[8][16]=58;
        timetable[10][0]=1;
        timetable[10][1]=5;
        timetable[10][2]=7;
        timetable[10][3]=10;
        timetable[10][4]=12;
        timetable[10][5]=16;
        timetable[10][6]=25;
        timetable[10][7]=31;
        timetable[10][8]=39;
        timetable[10][9]=47;
        timetable[10][10]=49;
        timetable[10][11]=53;
        timetable[12][0]=1;
        timetable[12][1]=3;
        timetable[12][2]=12;
        timetable[12][3]=17;
        timetable[12][4]=24;
        timetable[12][5]=31;
        timetable[12][6]=39;
        timetable[12][7]=46;
        timetable[12][8]=54;

        timetable[14][0]=2;
        timetable[14][1]=9;
        timetable[14][2]=12;
        timetable[14][3]=17;
        timetable[14][4]=24;
        timetable[14][5]=31;
        timetable[14][6]=39;
        timetable[14][7]=46;
        timetable[14][8]=54;

        timetable[15][0]=2;
        timetable[15][1]=9;
        timetable[15][2]=12;
        timetable[15][3]=15;
        timetable[15][4]=24;
        timetable[15][5]=31;
        timetable[15][6]=39;
        timetable[15][7]=47;
        timetable[15][8]=54;*/