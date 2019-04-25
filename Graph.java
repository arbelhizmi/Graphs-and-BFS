import java.util.ArrayList;
import java.util.Collections;

public class Graph<E> 
{
	private ArrayList<ArrayList<E>> data;

	public Graph()
	{
		this.data = new ArrayList<ArrayList<E>>();
	}

	public void addVertex(E ver) throws VertexExistException 			/// adding vertices to the graph
	{
		for(int i = 0; i < this.data.size(); i++)
		{
			if(this.data.get(i).get(0).equals(ver))
			{
				throw new VertexExistException(ver + " already exist");
			}
		}
		ArrayList<E> list = new ArrayList<>();
		list.add(ver);
		this.data.add(list);
	}

	public void addEdge(E ver1, E ver2) throws VertexNotExistException 
	{
		boolean ver1Exist = false;
		boolean ver2Exist = false;
		for (int i = 0; i < this.data.size(); i++)			//checks if ver1, ver2 are exists
		{
			if(this.data.get(i).get(0).equals(ver1))
			{
				ver1Exist = true;
			}
			if(this.data.get(i).get(0).equals(ver2))
			{
				ver2Exist = true;
			}
		}
		if(ver1Exist != true || ver2Exist != true)
		{
			throw new VertexNotExistException(ver1 + " or " + ver2 + "are not exist");
		}
		else
		{
			for(int j = 0; j < this.data.size(); j++)
			{
				if(this.data.get(j).get(0).equals(ver1))
				{
					this.data.get(j).add(ver2);
				}
				if(this.data.get(j).get(0).equals(ver2))
				{
					this.data.get(j).add(ver1);
				}
			}
		}	
	}

	public ArrayList<E> getEdges(E ver) throws VertexNotExistException 
	{
		ArrayList<E> edges = new ArrayList<E>();
		int indexOfVer = 0;
		boolean verExist = false;
		for(int i = 0; i < this.data.size(); i++)		// checks if ver is exist
		{
			if(this.data.get(i).get(0).equals(ver))
			{
				indexOfVer = i;							//getting the index of ver
				verExist = true;
			}
		}
		if(verExist == false)
		{
			throw new VertexNotExistException(ver + " not exist");
		}
		if(verExist == true)
		{
			if(this.data.get(indexOfVer).size() == 1)
			{
				return edges;
			}
			else
			{
				for(int k = 1; k < this.data.get(indexOfVer).size(); k++)			//adding the edges to "edges"
				{
					edges.add(this.data.get(indexOfVer).get(k));
				}
				return edges;
			}
		}
		return edges;
	}

	public ArrayList<E> getVertices() 
	{
		ArrayList<E> list = new ArrayList<>();
		if(this.data.size() == 0)
		{
			return list;
		}
		for(int i = 0; i < this.data.size(); i++)
		{
			list.add(this.data.get(i).get(0));		// adding the vertices of data
		}
		return list;
	}

	private int findIndexOfLastVer(E ver) 			//finding the location of the last ver
	{
		int indexOfLastVer = 0;
		for(int i = 0; i < this.data.size(); i++) 
		{
			if(this.data.get(i).get(0).equals(ver)) 
			{
				indexOfLastVer = i;
				break;
			}
		}
		return indexOfLastVer;
	}


	public ArrayList<E> bfs (E from, E to) 			
	{
		ArrayList<ArrayList<E>> datalist = new ArrayList<ArrayList<E>>();
		ArrayList<E> list = new ArrayList<E>();
		list.add(from);
		datalist.add(list);
		int indexOfLastVer = 0;
		while(datalist.isEmpty() == false)
		{
			list = new ArrayList<E>(datalist.get(0));
			datalist.remove(0);
			indexOfLastVer = findIndexOfLastVer(list.get(list.size( ) - 1));		// finding the location of the last element of the first list on the main list, on this.data
			for(int j = 0; j < this.data.get(indexOfLastVer).size() - 1; j++) 
			{
				ArrayList<E> templist = new ArrayList<E>(list);		
				if(templist.get(templist.size() - 1).equals(to)) 		//check if "to" was found
				{
					return templist;
				}
				if(templist.contains(this.data.get(indexOfLastVer).get(j + 1)) == false) 	// checks that the vertex is not already exist
				{
					templist.add(this.data.get(indexOfLastVer).get(j + 1));
					datalist.add(templist);
				}
			}
		}
		return new ArrayList<E>();
	}
}