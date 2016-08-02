package com.example.admin.fashion;

import java.util.ArrayList;

/**
 * Created by admin on 2016/08/02.
 */

//木のノードを表現するクラス
public class Decisionnode {
    int col;    //テストされる基準のインデックス値
    int value;  //値
    String result; //帰結のディクショナリ　終点以外はnull
    ArrayList<Decisionnode> node;   //次のノード

    Decisionnode(int c, int v, String r){
        col = c;
        value = v;
        result = r;
    }
}
