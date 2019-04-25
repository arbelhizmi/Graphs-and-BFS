import java.util.ArrayList;
import java.util.Collections;

public class SocialNetwork 
{
	Graph<String> network;

	public SocialNetwork()
	{
		this.network = new Graph<>();
	}

	public void addUser(String user) throws UserExistException 	//try,catch for adding users to the graph
	{
		try 
		{
			this.network.addVertex(user);
		}
		catch (VertexExistException e) 
		{
			throw new UserExistException(user +  " alreay exist");
		}
	}

	public void addFriends(String user1, String user2) throws UserNotFoundException 
	{
		try
		{
			this.network.addEdge(user1, user2);
		} 
		catch (VertexNotExistException e)
		{
			throw new UserNotFoundException(user1 +  " or " + user2 + " are not exist");			//if vertex is not exist throws UserNotExistException
		}
	}

	public boolean knows(String user1, String user2) throws UserNotFoundException			
	{
		if(!(this.network.bfs(user1, user2).isEmpty()))
		{
			return true;
		}

		if(this.network.bfs(user1, user2).isEmpty())
		{
			return false;
		}
		else
		{
			throw new UserNotFoundException(user1 +  " or " + user2 + " are not exist");
		}
	}







}




