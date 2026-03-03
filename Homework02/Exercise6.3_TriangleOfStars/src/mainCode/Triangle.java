package mainCode;

public class Triangle {
	int height;
	String triangle = "";
	
	Triangle(int x)
	{
		height = x;
	}
	
	void createTriangle()
	{
		String space = "";
		String star = "";
		for(int i = 1; i <= height; i++)
		{
			space = space + " ";
		}
		
		for(int i = 0; i < height; i++)
		{
			triangle = triangle + space + star + "*" + star + space;
			star = star + "*";
			space = space.substring(0, space.length() - 1);
			triangle = triangle + "\n";
		}
	}
	
	void printTriangle()
	{
		System.out.println(triangle);
	}
}
