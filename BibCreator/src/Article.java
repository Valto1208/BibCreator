
public class Article {

	
	private long id;
	private String author;
	private String journal;
	private String title;
	private int year;
	private String volume;
	private int number;
	private String pages;
	private String keywords;
	private String doi;
	private String ISSN;
	private String month;
	static int counter = 0;
	
	
	// Constructor
	public Article() {
		this.id = 0;
		this.author = "";
		this.journal = "";
		this.title = "";
		this.year = 0;
		this.volume = "";
		this.number = 0;
		this.pages = "";
		this.keywords = "";
		this.doi = "";
		ISSN = "";
		this.month = "";
		counter++;
	}
	public Article(long id, String author, String journal, String title, int year, String volume, int number,
			String pages, String keywords, String doi, String iSSN, String month) {
		this.id = id;
		this.author = author;
		this.journal = journal;
		this.title = title;
		this.year = year;
		this.volume = volume;
		this.number = number;
		this.pages = pages;
		this.keywords = keywords;
		this.doi = doi;
		ISSN = iSSN;
		this.month = month;
		counter++;
	}
	
	// Getters and Setters 
	public int getCounter() {
		return counter;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getJournal() {
		return journal;
	}


	public void setJournal(String journal) {
		this.journal = journal;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getVolume() {
		return volume;
	}


	public void setVolume(String volume) {
		this.volume = volume;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getPages() {
		return pages;
	}


	public void setPages(String pages) {
		this.pages = pages;
	}


	public String getKeywords() {
		return keywords;
	}


	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	public String getDoi() {
		return doi;
	}


	public void setDoi(String doi) {
		this.doi = doi;
	}


	public String getISSN() {
		return ISSN;
	}


	public void setISSN(String iSSN) {
		ISSN = iSSN;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}
	/* IEEE
 	 * J. Park, J. N. James, Q. Li, Y. Xu, W. Huang. "Optimal DASH-Multicasting over LTE", IEEE
			 Transactions on Vehicular Technology, vol. PP, no. 99, p. 15-27, January 2018
 	 */
	 
	 /* ACM
	  * J. Park et al. 2018. Optimal DASH-Multicasting over LTE. IEEE Transactions on Vehicular
		Technology. PP, 99 (2018), 15-27. DOI:https://doi.org/10.1109/TVT.2018.2789899.
	  */
	 
	 /* NJ
	  * J. Park & J. N. James & Q. Li & Y. Xu & W. Huang. Optimal DASH-Multicasting over LTE. IEEE
		Transactions on Vehicular Technology. PP, 15-27(2018).
	  */
	public String IEEE_Format() {
		
		String newAuthor = this.author.replace("and", ",");
		
		return  newAuthor + ".\"" + this.title + "\"," + this.journal + ", vol. " + this.volume + ", no. " +
		this.number + ", p. " + this.pages + ", " + this.month +  " " + this.year + "\n";

	}
	
	public String ACM_Format() {
		
		String[] array1 = this.author.split("and");
		String[] array2 = this.doi.split("/");
		
		return array1[0] + "et al." + this.year + "." + this.title + ". " + this.journal + ". PP, " + this.volume + " (" + this.year + "), " + this.pages +  ". " + 
		"DOI:https://doi.org/" + array2[0] + "/" + array2[1] + ".\n" ;
	}
	
	public String NJ_Format() {
		
		String newAuthor = this.author.replace("and", "&");
		
		return newAuthor + ". " + this.title + ". " + this.journal + ". PP, " +
		this.pages + "(" + this.year + ").\n";
	}


	
	
}
