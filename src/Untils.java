
public class Untils {

public static String FirstChar2Up(String str){
	str=str.trim();
	if(str.equals("")||str==null){
		return null;
	}
	str=str.substring(0, 1).toUpperCase()+str.substring(1);
	return str;
}

public static String lastChar2Low(String str){
	str=str.trim();
	if(str.equals("")||str==null){
		return null;
	}
	str=str.substring(0, 1).toLowerCase()+str.substring(1);
	return str;
}


}
