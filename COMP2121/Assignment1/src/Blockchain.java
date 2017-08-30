import java.util.ArrayList;

public class Blockchain {

	private Block head;
	private ArrayList<Transaction> pool;
	private int length;

	private final int poolLimit = 3;

	public Blockchain() {
		pool = new ArrayList<>();
		length = 0;
	}

	// getters and setters
	public Block getHead() {
		return head;
	}

	public ArrayList<Transaction> getPool() {
		return pool;
	}

	public int getLength() {
		return length;
	}

	public void setHead(Block head) {
		this.head = head;
	}

	public void setPool(ArrayList<Transaction> pool) {
		this.pool = pool;
	}

	public void setLength(int length) {
		this.length = length;
	}

	// add a transaction
	public int addTransaction(String txString) {
		if (txString.length() == 4 )
		{
			return 0;
		}

		if ( txString.matches("^tx|[a-z]{4}[0-9]{4}|.*{0,70}") == false ){
			return 0;
		}
		
		if ( pool.size() == poolLimit ){
			Block newBlock = new Block();
			newBlock.setTransactions(this.pool);
			this.setHead( newBlock);
			ArrayList<Transaction> pool = new ArrayList<>(3);
			this.setPool( pool );
			this.setLength(this.getLength() + 1);
			return 2;
		}else{
			Transaction trans = new Transaction();
			String[] str = txString.split("|");
			trans.setSender(str[1]);
			trans.setContent(str[2]);
			if(str[2].contains("|")){
				return 0;
			}
			this.pool.add(trans);
			return 1;
		}
	}

	public String toString() {
		String cutOffRule = new String(new char[81]).replace("\0", "-") + "\n";
		String poolString = "";
		for (Transaction tx : pool) {
			poolString += tx.toString();
		}

		String blockString = "";
		Block bl = head;
		while (bl != null) {
			blockString += bl.toString();
			bl = bl.getPreviousBlock();
		}

		return "Pool:\n" + cutOffRule + poolString + cutOffRule + blockString;
	}

	// implement helper functions here if you need any.
}
