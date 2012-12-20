package com.example.listviewsample;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

ListView listView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //xml 문서에 정의된 ListView 객체의 참조값 얻어오기
        listView = (ListView)findViewById(R.id.listView);
        //아답터에 연결할 모델 만들기
        ArrayList<MyItem> list = new ArrayList<MyItem>();
        list.add(new MyItem(1, "아도겐"));
        list.add(new MyItem(2, "나무1"));
        list.add(new MyItem(3, "나무2"));
        list.add(new MyItem(1, "아도겐"));
        list.add(new MyItem(2, "나무1"));
        list.add(new MyItem(3, "나무2"));
        list.add(new MyItem(1, "아도겐"));
        list.add(new MyItem(2, "나무1"));
        list.add(new MyItem(3, "나무2"));
        
        //아답터 객체 생성하기
        MyAdapter adapter = new MyAdapter(this, R.layout.custom, list);
        //아답터를 listView 객체에 연결하기
        listView.setAdapter(adapter);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
