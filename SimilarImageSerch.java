package mp1final;
/*
類似画像検索ソフト
濃度、ラン平均、ラン標準偏差、重心を特徴量としてシンボルマークの画像から入力された画像に類似している
画像を上位１０個表示する
*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import GImage.GImage;

public class SimilarImageSerch {
	private GImage img;
	static ArrayList<Double> f1 = new ArrayList<>();
	static ArrayList<Double> f2 = new ArrayList<>();
	static ArrayList<Double> f3 = new ArrayList<>();
	static ArrayList<Double> f4 = new ArrayList<>();
	static ArrayList<Double> f5 = new ArrayList<>();
	static ArrayList<Double> f6 = new ArrayList<>();
	static ArrayList<Double> f7 = new ArrayList<>();
	static ArrayList<Double> f8 = new ArrayList<>();
	static ArrayList<ArrayList<Double>> z = new ArrayList<>();

	double X =0;
	double Y =0;
	int Xp =0;
	int Xm=0;
	int Yp=0;
	int Ym=0;
	double count=0;
public SimilarImageSerch(GImage img) {
	
		// TODO 自動生成されたコンストラクター・スタブ
	this.img =img;
	}


public void XandY (GImage img){
	
	for(int n= 0;n<=255;n++){
		for(int m= 0;m<=255;m++){
			if(img.pixel[n][m]==0){
				this.Yp=n;
				break;
			}
		}
		if(this.Yp!=0){
			break;
		}
	}
	
	for(int n= 0;n<=255;n++){
		for(int m= 0;m<=255;m++){
			if(img.pixel[m][n]==0){
				this.Xp=n;
				break;
			}
		}
		
		if(this.Xp!=0){
			break;
		}
	}
	
	for(int n= 255;n>=0;n--){
		for(int m= 255;m>=0;m--){
			if(img.pixel[n][m]==0){
				this.Ym=n;
			
				break;
			}
		}
		if(this.Ym!=0){
		
			break;
		}
	}
	
	for(int n= 255;n>=0;n--){
		for(int m= 255;m>=0;m--){
			if(img.pixel[m][n]==0){
				this.Xm=n;
				
				break;	
			}
		}
		
		if(this.Xm!=0){
			break;
		}
	}
	
	//黒画素カウント
	for(int n= 0;n<=255;n++){
		for(int m= 0;m<=255;m++){
			if(img.pixel[n][m]==0){
				this.count++;
				
			}
			
		}
	}
	
	this.X=Xm-Xp+1;
	this.Y=Ym-Yp+1;
	
	
}

public double noudo(){
	double B =count/(X*Y);

	return B;
}
public double gaisetu(){
	return Y/X;
}

public double HRan(){
	double co=0;
	for(int n=Yp;n<=Ym;n++){
		for(int m= Xp;m<=Xm;m++){
			if(img.pixel[n][m]==0 && img.pixel[n][m+1]==255){
				co++;
			}
			
		}
	}
	return co/Y;
	
}

public double VRan(){
	double co=0;
	for(int n=Xp ;n<=Xm;n++){
		for(int m= Yp;m<=Ym;m++){
			if(img.pixel[m][n]==0 && img.pixel[m+1][n]==255){
				co++;
			}
			
		}
	}
	return co/X;
	
}
public double HRanH(){
	ArrayList<Double> ff5 = new ArrayList<>();
	double co=0;
	double to =0;
	double nco=0;
	for(int n=Yp ;n<=Ym;n++){
		nco=0;
		for(int m= Xp;m<=Xm;m++){
			if(img.pixel[n][m]==0 && img.pixel[n][m+1]==255){
				co++;
				nco++;
			}
		}
		ff5.add(nco);
		
	}
	
	
	double u =co/Y;
	for(int n=0 ;n<=Ym-Yp;n++){
		to += (ff5.get(n)-u)*(ff5.get(n)-u);
		}
	
	return  (Math.sqrt(to/Y));
	
}

public double VRanH(){
	ArrayList<Double> ff6 = new ArrayList<>();
	double co=0;
	double to =0;
	double nco=0;
	for(int n=Xp;n<=Xm;n++){
		nco =0;
		for(int m= Yp;m<=Ym;m++){
			if(img.pixel[m][n]==0 && img.pixel[m+1][n]==255){
				co++;
				nco++;
			}
		}
		ff6.add(nco);
	}
	double u =co/X;
	for(int n=0 ;n<=Xm-Xp;n++){
		to += (ff6.get(n)-u)*(ff6.get(n)-u);
		}
	
	return  (Math.sqrt(to/X));
	
}

public double Xjusin(){
	double coX=0;
	for(int n=Yp ;n<=Ym;n++){
		for(int m= Xp;m<=Xm;m++){
			if(img.pixel[n][m]==0){
				coX+=m-Xp;
			}
		}
	}
	return  Math.round(coX/count);
	
}

public double Yjusin(){
	double coY=0;
	for(int n=Yp ;n<=Ym	;n++){
		for(int m= Xp;m<=Xm;m++){
			if(img.pixel[n][m]==0){
				coY+=n-Yp;
			}
		}
	}
	return Math.round(coY/count);
}
	

	
public ArrayList<Double> seikika(ArrayList<Double> list1){
	ArrayList<Double> array= new ArrayList<>();
	double x1=0;
	double r =0;
		for(int i=0;i<list1.size();i++){
			x1 += list1.get(i);
		}
		x1=x1/list1.size();
		for(int i=0;i<list1.size();i++){	
			r += (list1.get(i)-x1)*(list1.get(i)-x1);  
		}
		r=Math.sqrt(r/list1.size());
		for(int i=0;i<list1.size();i++){
			array.add((list1.get(i)-x1)/r);		
	}
		System.out.println(list1);
		return array;
}
public static void main(String[] args) {
	ArrayList <Double> d= new ArrayList<>();
	ArrayList<Double> fx = new ArrayList<>();
	Map<Integer,Double> answer = new HashMap<>();
	Map<Double, Integer> answer2 =new HashMap<>();
	ArrayList<ArrayList<Double>> z1 = new ArrayList<>();
	
	SimilarImageSerch ex1 = null;
	Scanner scan = new Scanner(System.in);
	//検索される画像集合を読み込む
	//各特徴量を計算しarryListに加える
	for (int i = 0; i < 100; i++) {
		String s = i+1 + ".bmp";
		GImage img= new GImage("/Users/haruki/Documents/workspace/MPFinal/2018-mp1-final1-data/" + s);
		ex1 =new SimilarImageSerch(img);
		ex1.XandY(img);
		f1.add(ex1.noudo());
		f2.add(ex1.gaisetu());
		f3.add(ex1.HRan());
		f4.add(ex1.VRan());
		f5.add(ex1.HRanH());
		f6.add(ex1.VRanH());
		f7.add(ex1.Xjusin());
		f8.add(ex1.Yjusin());
		
	}
	//各特徴量を正規化した値をが入っているarrayListを二次元arrayListに加える
	z1.add(ex1.seikika(f1));
	z1.add(ex1.seikika(f2));
	z1.add(ex1.seikika(f3));
	z1.add(ex1.seikika(f4));
	z1.add(ex1.seikika(f5));
	z1.add(ex1.seikika(f6));
	z1.add(ex1.seikika(f7));
	z1.add(ex1.seikika(f8));
	
	//検索する画像の入力
	System.out.print("画像の番号を入力してください");
	String number = scan.nextLine();
	//用いる特徴量の選択するための入力
	System.out.print("検索する特徴量のアルファベットを入力してください");
	String v =scan.nextLine();
	fx.add(z1.get(0).get(Integer.valueOf(number)-1));
	fx.add(z1.get(1).get(Integer.valueOf(number)-1));
	fx.add(z1.get(2).get(Integer.valueOf(number)-1));
	fx.add(z1.get(3).get(Integer.valueOf(number)-1));
	fx.add(z1.get(4).get(Integer.valueOf(number)-1));
	fx.add(z1.get(5).get(Integer.valueOf(number)-1));
	fx.add(z1.get(6).get(Integer.valueOf(number)-1));
	fx.add(z1.get(7).get(Integer.valueOf(number)-1));
	double value ;
	//特徴量の選択されたものの検索画像と検索される画像の距離を二乗したものを加算
		for(int i=0;i<100;i++){
			value =0;
			if(v.contains("f1")){//濃度
				value+=(fx.get(0)-z1.get(0).get(i))*(fx.get(0)-z1.get(0).get(i));
			}
			if(v.contains("f2")){//縦横比
				value+=(fx.get(1)-z1.get(1).get(i))*(fx.get(1)-z1.get(1).get(i));
			}
			if(v.contains("f3")){//水平ラン平均
				value+=(fx.get(2)-z1.get(2).get(i))*(fx.get(2)-z1.get(2).get(i));
			}
			if(v.contains("f4")){//垂直ラン平均
				value+=(fx.get(3)-z1.get(3).get(i))*(fx.get(3)-z1.get(3).get(i));
			}
			if(v.contains("f5")){//水平ラン標準偏差
				value+=(fx.get(4)-z1.get(4).get(i))*(fx.get(4)-z1.get(4).get(i));
			}
			if(v.contains("f6")){//垂直ラン標準偏差
				value+=(fx.get(5)-z1.get(5).get(i))*(fx.get(5)-z1.get(5).get(i));
			}
			if(v.contains("f7")){//重心x座標
				value+=(fx.get(6)-z1.get(6).get(i))*(fx.get(6)-z1.get(6).get(i));
			}
			if(v.contains("f8")){//重心y座標
				value+=(fx.get(7)-z1.get(7).get(i))*(fx.get(7)-z1.get(7).get(i));	
			}
			d.add(value);
		}
		//平方根をとり距離を計算して画像番号と紐付け
		for(int i=0;i<100;i++){
			answer.put(i+1, Math.sqrt(d.get(i)));
			answer2.put(Math.sqrt(d.get(i)),i+1 );
		}
		List<Double> list2 = new ArrayList<>();
		for(int i =0;i<100;i++){
			list2.add(answer.get(i+1));
		}
		//距離の近い順に並び替えをし画像番号を出力
		Collections.sort(list2);
		for(int i =1;i<=10;i++){
		System.out.println(answer2.get(list2.get(i)));
		}
		//10位までの情報をテキストファイルとして出力
		try {
            //出力先を作成する
            FileWriter fw = new FileWriter("/Users/haruki/Documents/workspace/MPFinal/順位.txt", false); 
          //ファイルに書き出す
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

            //内容を指定する
            pw.print("検索に用いた特徴量: "+v);
            pw.println(",検索キー: "+number);
            for(int i=1;i<=10;i++){
            pw.println(i+"位  "+answer2.get(list2.get(i)));
            pw.println("特徴量　f1:"+f1.get(answer2.get(list2.get(i))-1)+"  f2:"+f2.get(answer2.get(list2.get(i))-1)+"  f3:"+f3.get(answer2.get(list2.get(i))-1)
            +"  f4:"+f4.get(answer2.get(list2.get(i))-1)+"  f5:"+f5.get(answer2.get(list2.get(i))-1)+"  f6:"+f6.get(answer2.get(list2.get(i))-1)+"  f7:"+f7.get(answer2.get(list2.get(i))-1)+"  f8:"+f8.get(answer2.get(list2.get(i))-1));
            pw.println("距離:"+answer.get(answer2.get(list2.get(i))));
            }
            pw.close();
            //終了メッセージを画面に出力する
            System.out.println("出力が完了しました。");
        } catch (IOException ex) {
            //例外時処理
            ex.printStackTrace();
        }
		  //10位までの画像ファイルを保存するディレクトリの作成
		  File newfile = new File("/Users/haruki/Documents/workspace/MPFinal/"+number+".bmpの類似画像ファイル");
		  newfile.mkdir();  
		
		for(int i=1;i<=10;i++){
			//保存先のディレクトリの指定とファイル名の設定
			String fileName = "/Users/haruki/Documents/workspace/MPFinal/"+number+".bmpの類似画像ファイル/"+String.valueOf(answer2.get(list2.get(i)));
			String fileType = "bmp";
			GImage img= new GImage("/Users/haruki/Documents/workspace/MPFinal/2018-mp1-final1-data/" +String.valueOf(answer2.get(list2.get(i)))+ ".bmp" );	

			img.output(fileName, fileType);
		}
		
		System.out.println(8+"位  ");
		System.out.println("特徴量　f1:"+f1.get(8-1)+"  f2:"+f2.get(8-1)+
				"  f3:"+f3.get(8-1)  +"  f4:"+f4.get(8-1)+"  f5:"+f5.get(8-1)+"  f6:"+f6.get(8-1)+"  f7:"+f7.get(8-1)+"  f8:"+f8.get(8-1));
		System.out.println("距離:"+answer.get(8));
		System.out.println(14+"位  ");
		System.out.println("特徴量　f1:"+f1.get(14-1)+"  f2:"+f2.get(14-1)+
				"  f3:"+f3.get(14-1)  +"  f4:"+f4.get(14-1)+"  f5:"+f5.get(14-1)+"  f6:"+f6.get(14-1)+"  f7:"+f7.get(14-1)+"  f8:"+f8.get(14-1));
		System.out.println("距離:"+answer.get(14));
		System.out.println(17+"位  ");
		System.out.println("特徴量　f1:"+f1.get(17-1)+"  f2:"+f2.get(17-1)+
				"  f3:"+f3.get(17-1)  +"  f4:"+f4.get(17-1)+"  f5:"+f5.get(17-1)+"  f6:"+f6.get(17-1)+"  f7:"+f7.get(17-1)+"  f8:"+f8.get(17-1));
		System.out.println("距離:"+answer.get(17));
		System.out.println(67+"位  ");
		System.out.println("特徴量　f1:"+f1.get(67-1)+"  f2:"+f2.get(67-1)+
				"  f3:"+f3.get(67-1)  +"  f4:"+f4.get(67-1)+"  f5:"+f5.get(67-1)+"  f6:"+f6.get(67-1)+"  f7:"+f7.get(67-1)+"  f8:"+f8.get(67-1));
		
		scan.close();
	}
}

	
