package com.example.listviewsample;

import android.widget.BaseAdapter;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	 //필요한 멤버 필드 만들기
	 Context context;
	 //셀 하나에 대한 레이아웃 정보를 가지고 있는 리소스(R.layout.custom)
	 int layoutRes;
	 //셀에 출력할 데이터를 가지고 있는 배열(이미지 리소스, 텍스트) - MyItem.class로 만든다.
	 ArrayList<MyItem> list;
	 //셀 하나하나에 레이아웃을 전개할 레이아웃 전개자 객채(*)
	 LayoutInflater inflater;
	 //생성자 만들기(레이아웃 전개를 위해 context가 필요하다)
	 public MyAdapter(Context context, int layoutRes, ArrayList<MyItem> list){
	  this.context = context;
	  this.layoutRes = layoutRes;
	  this.list = list;
	  //레이아웃 전개자 객체 얻어오기(*)(custom.xml파일을 읽어들여서 View객체로 전개해주는 클래스)
	  inflater  = LayoutInflater.from(context);
	  
	 }
	 //아이템의 총 갯수를 리턴하는 메소드
	 public int getCount() {
	  
	  return list.size();
	 }
	 //원하는 인덱스의 아이템을  (모델) 리턴하는 메소드
	 public Object getItem(int position) {
	  
	  return list.get(position);
	 }
	 //원하는 인덱스의 아이템 아이디를 리턴하는 메소드
	 public long getItemId(int position) {
	  //인덱스값은 중복이 되지 않으므로 인덱스값을 그냥 아이디 값으로 사용하면 된다.
	  return position;
	 }
	 //int getViewCount=0;
	 //int convertViewCount=0;
	 //리스트뷰의 셀을 구성할 때 호출되는 메소드(스크롤이 움직이면 새로운 데이터들이 보여지게 되는데     
	       //보여질때마다 이 메소드가 호출된다. 이 메소드 안에서 하나의 셀 모습으로 조립을 하고 리턴해준다.
	        //(번호(position), 한 행에 해당하는 뷰(convertView), 메인ListView(parent))
	 public View getView(int position, View convertView, ViewGroup parent) {
	  //getViewCount++;
	  //Log.e("getView()","호출된 횟수"+getViewCount);
	  //필요한 셀을 레이아웃 정보와 모델을 바탕으로 구성한 후 구성한 셀(View)를 리턴한다.
	  final int INDEX=position;
	//다음 행이 비어있다면 custom.xml의 형태로 다음행을 만들어준다. 만든 뒤에 이미지, 글자, 버튼을 등록한다.
	  if(convertView==null){
	   //convertViewCount++;
	   //Log.e("convertView","호출된 횟수"+convertViewCount);
	   //커스터마이징한 셀 레이아웃을 이용해서 뷰를 만든다.(*)
	   convertView=inflater.inflate(layoutRes, parent, false);
	   
	  }
	  ImageView image = (ImageView)convertView.findViewById(R.id.image);
	  //리스트에 담겨있는 출력할 이미지 리소스를 읽어온다.
	 // image.setImageResource(list.get(INDEX).imgRes);
	  TextView text = (TextView)convertView.findViewById(R.id.text);
	  //리스트에 담겨있는 출력할 텍스트를 얻어온다.
	  text.setText(list.get(INDEX).name);
	  Button btn = (Button)convertView.findViewById(R.id.button);
	  //버튼에 리스너 등록하기(버튼을 누르면 이미지를 한화면 보여주는 Intent 이동을 한다)
	  btn.setOnClickListener(new Button.OnClickListener(){
	   public void onClick(View v){
	    
	  //이미지 리소스를 전달한 다음
//	       DetailActivity.imageRes=list.get(INDEX).imgRes;
//	       //액티비티를 이동한다.
//	       Intent intent = new Intent(context, DetailActivity.class);
//	       context.startActivity(intent);
	       
	   }
	  });
	  //구성한 뷰를 리턴한다.
	  return convertView;
	 }

}