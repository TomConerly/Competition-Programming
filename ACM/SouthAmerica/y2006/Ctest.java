package ACM.SouthAmerica.y2006;

//generates a hard test case for C
public class Ctest {
	public static void main(String[] args)
	{
		String start = "abcdefghijklmnopqrstuvwxyz____";
		String[] artist = {"abcdefghijklmnopqrstuvwxyz____","bcdefghijklmnopqrstuvwxyz____a","cdefghijklmnopqrstuvwxyz____ab","defghijklmnopqrstuvwxyz____abc","efghijklmnopqrstuvwxyz____abcd","fghijklmnopqrstuvwxyz____abcde"};
		for(int i = 0; i < 30;i++)
		{
			System.out.println(start.substring(i)+start.substring(0,i));
			System.out.println(artist[i/5]);
		}
	}
}
