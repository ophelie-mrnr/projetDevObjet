package Interface;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import DAO.MaConnexion;


public class TimeSeriePanel{

	private static final Logger LOGGER = Logger.getLogger("myLogger");

	ChartPanel cp ; 
	TimeSeriesCollection tsc;
	JFreeChart tc;

	public  TimeSeriePanel (String charTitle){
		tsc= new TimeSeriesCollection();
		tc = createTimeChart(tsc, charTitle);
		cp = new ChartPanel(tc);

	}

	public void addNewSeries(String title, String dateDeb, String dateFin, String pays){
		tsc.addSeries(createTimeSeries(title, dateDeb, dateFin, pays));
	}

	public void clean(){
		tsc.removeAllSeries();
	}

	public static TimeSeries createTimeSeries(String title, String dateDeb, String dateFin, String pays){

		TimeSeries timeSeries ;
		if (pays=="All")
			timeSeries = new TimeSeries("ventes partout du "+ dateDeb+ " au "+ dateFin+"");
		else 
			timeSeries = new TimeSeries("ventes en "+pays+" du "+ dateDeb+ " au "+ dateFin+"");
		java.sql.Statement state0;
		java.sql.Statement state;
		java.sql.Statement state1;
		java.sql.Statement state2;
		ArrayList<String> liste = new ArrayList<String>(); 

		int revenu =0;
		int jourDeb =0 , moisDeb =0 , anneeDeb=0;
		int jourFin=0, moisFin=0, anneeFin=0;

		// On cherche a mettre les deux dates sous forme : jj-mm-aaaa

		// Si l'utilisateur n'a pas mis de date la date de debut va corresprondre a la premiere date de la bdd 
		if (dateDeb.equals("aaaa-mm-jj") || dateDeb.equals("")  ){
			try {
				state = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				String query = "SELECT orderDate FROM orders;";
				ResultSet res0 =state.executeQuery(query);
				res0.next();
				dateDeb = res0.getString(1);
			}
			catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Exception occur", e);
				e.printStackTrace();
			}
		}

		jourDeb = jourToInt(dateDeb);
		moisDeb = moisToInt(dateDeb);
		anneeDeb = anneeToInt(dateDeb);
		dateDeb = dateToString(jourDeb,moisDeb , anneeDeb);

		// Si l'utilisateur n'a pas mis la date, la date de din correspondra a la date la plus récente
		if (dateFin.equals("aaaa-mm-jj") || dateFin.equals("")  ){
			try {
				state = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				String query = "SELECT orderDate FROM orders;";
				ResultSet res0 =state.executeQuery(query);
				while (res0.next()){
				dateFin = res0.getString(1);
				}
			}
			catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Exception occur", e);
				e.printStackTrace();
			}
		}

		jourFin = jourToInt(dateFin);
		moisFin = moisToInt(dateFin);
		anneeFin = anneeToInt(dateFin);
		dateFin = dateToString(jourFin, moisFin, anneeFin );


		//On incrémente le jour de fin pour aller jusque jour de fin voulu
		dateFin = suivant(jourToInt(dateFin),moisToInt(dateFin),anneeToInt(dateFin));


		// Faire PAYS
		if (pays=="All"){
			while(dateDeb.equals(dateFin)==false){
				try {
					state = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

					String query = "SELECT * FROM orders WHERE orderDate = '" + dateDeb +"';";

					ResultSet res =state.executeQuery(query);

					liste = new ArrayList<String>(); 
					while (res.next()){
						String resultat = res.getString("orderNumber");
						liste.add(resultat);
					}

					if (liste.size()!=0){
						for (int i = 0; i<liste.size(); i++){
							try{
								ArrayList<Integer> listePrix = new ArrayList<Integer>(); 
								ArrayList<Integer> listeQuantite = new ArrayList<Integer>(); 
								state1 = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
								String query1 = "SELECT priceEach FROM Orderdetails WHERE orderNumber= '"+liste.get(i)+"';";
								ResultSet res1 =	state1.executeQuery(query1);

								while (res1.next()){
									int resultatPrix = res1.getInt("priceEach");
									listePrix.add(resultatPrix);
								}
								try{
									state2 = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
									String query2 = "SELECT quantityOrdered FROM Orderdetails WHERE orderNumber= '"+liste.get(i)+"';";
									ResultSet res2 =state2.executeQuery(query2);

									while (res2.next()){
										int resultatQuantite = res2.getInt("quantityOrdered");
										listeQuantite.add(resultatQuantite);
									}
									revenu = 0;
									for(int j =0; j<listePrix.size(); j++){
										revenu += (listePrix.get(j)*listeQuantite.get(j));
									}

								}
								catch (SQLException e3){
									LOGGER.log(Level.SEVERE, "Exception occur", e3);
									e3.printStackTrace();
								}
							}
							catch (SQLException e2) {
								LOGGER.log(Level.SEVERE, "Exception occur", e2);
								e2.printStackTrace();
							}
						}
					}
				} 
				catch (SQLException e1) {
					LOGGER.log(Level.SEVERE, "Exception occur", e1);
					e1.printStackTrace();
				}
				timeSeries.addOrUpdate(new Day(jourDeb,moisDeb,anneeDeb), revenu);
				// incrementation de la date
				dateDeb = suivant(jourDeb,moisDeb,anneeDeb);
				jourDeb = jourToInt(dateDeb);
				moisDeb = moisToInt(dateDeb);
				anneeDeb = anneeToInt(dateDeb);
				jourFin = jourToInt(dateFin);
				moisFin = moisToInt(dateFin);
				anneeFin = anneeToInt(dateFin);
			}
		}

		else {
			try {
				state0 = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				String query0 = "SELECT customerNumber FROM customers WHERE country = '" + pays +"';";
				ResultSet res0 =state0.executeQuery(query0);

				ArrayList<String> listeCustomersNumber = new ArrayList<String>();

				while (res0.next()){
					String resultatPays = res0.getString("customerNumber");
					listeCustomersNumber.add(resultatPays);
				}
				while(dateDeb.equals(dateFin)==false){
					try {
						state = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

						for(int k = 0; k<listeCustomersNumber.size(); k++){
							String query = "SELECT * FROM orders WHERE orderDate = '" + dateDeb +"' AND customerNumber = '"+listeCustomersNumber.get(k)+"';";

							ResultSet res =state.executeQuery(query);

							liste = new ArrayList<String>(); 

							while (res.next()){
								String resultat = res.getString("orderNumber");
								liste.add(resultat);
							}
						}	
						if (liste.size()!=0){
							for (int i = 0; i<liste.size(); i++){
								try{
									ArrayList<Integer> listePrix = new ArrayList<Integer>(); 
									ArrayList<Integer> listeQuantite = new ArrayList<Integer>(); 
									state1 = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
									String query1 = "SELECT priceEach FROM Orderdetails WHERE orderNumber= '"+liste.get(i)+"';";
									ResultSet res1 =	state1.executeQuery(query1);

									while (res1.next()){
										int resultatPrix = res1.getInt("priceEach");
										listePrix.add(resultatPrix);
									}
									try{
										state2 = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
										String query2 = "SELECT quantityOrdered FROM Orderdetails WHERE orderNumber= '"+liste.get(i)+"';";
										ResultSet res2 =state2.executeQuery(query2);

										while (res2.next()){
											int resultatQuantite = res2.getInt("quantityOrdered");
											listeQuantite.add(resultatQuantite);
										}
										revenu = 0;
										for(int j =0; j<listePrix.size(); j++){
											revenu += (listePrix.get(j)*listeQuantite.get(j));
										}

									}
									catch (SQLException e3){
										LOGGER.log(Level.SEVERE, "Exception occur", e3);
										e3.printStackTrace();
									}
								}
								catch (SQLException e2) {
									LOGGER.log(Level.SEVERE, "Exception occur", e2);
									e2.printStackTrace();
								}
							}
						}
					} 
					catch (SQLException e1) {
						LOGGER.log(Level.SEVERE, "Exception occur", e1);
						e1.printStackTrace();
					}
					timeSeries.addOrUpdate(new Day(jourDeb,moisDeb,anneeDeb), revenu);

					// incrementation de la date
					dateDeb = suivant(jourDeb,moisDeb,anneeDeb);
					jourDeb = jourToInt(dateDeb);
					moisDeb = moisToInt(dateDeb);
					anneeDeb = anneeToInt(dateDeb);
					jourFin = jourToInt(dateFin);
					moisFin = moisToInt(dateFin);
					anneeFin = anneeToInt(dateFin);
				}
			}
			catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Exception occur", e);
				e.printStackTrace();
			}
		}
		return timeSeries;
	}


	public static JFreeChart createTimeChart(TimeSeriesCollection dataset, String title){
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				title,
				"Dates",
				"Ventes",
				dataset);
		return chart;
	}

	// Permet de convertir le jour en int
	public static int jourToInt(String s){
		String jour = s;
		String mois = s;
		String annee = s;
		// Initialisation 
		int premierTrait =jour.indexOf("-");
		annee = annee.substring(0, premierTrait);
		mois = mois.substring(premierTrait+1);
		int secondTrait = mois.indexOf("-");
		mois = mois.substring(0, secondTrait);
		jour = jour.substring(premierTrait+secondTrait+2);

		// Conversion en int
		int j = Integer.parseInt(jour); 
		return j;
	}

	// Permet de convertir le mois en int
	public static int moisToInt(String s){
		String mois = s;
		String annee = s;
		// Initialisation 
		int premierTrait =annee.indexOf("-");
		annee = annee.substring(0, premierTrait);
		mois = mois.substring(premierTrait+1);
		int secondTrait = mois.indexOf("-");
		mois = mois.substring(0, secondTrait);
		// conversion en int
		int m = Integer.parseInt(mois); 
		return m;
	}

	// Permet de convertir l'année en int
	public static int anneeToInt(String s){

		String annee = s;
		// Initialisation 
		int premierTrait =annee.indexOf("-");
		annee = annee.substring(0, premierTrait);
		// conversion en int
		int a = Integer.parseInt(annee);
		return a;
	}

	// Permet de mettre les date en string sous forme jj/mm/aaaa (case respectee)
	public static String dateToString(int j, int m, int a){
		String jour;
		String mois;
		String annee;

		// Remise de la date sous forme de String
		if(j<10){
			jour = "0"+j;
		}
		else jour = ""+j;

		if(m<10){
			mois = "0"+m;
		}
		else mois = ""+m;
		annee = ""+a;

		String s = annee+"-"+mois+"-"+jour;
		return s;
	}

	// Fonction qui permet de savoir si une annee est bissextile
	public  static boolean bissextile(int a){ 
		if((a%4==0 && a%100!=0)||(a%400 == 0)){
			return true;
		}else {
			return false;
		}
	}

	// Permet d'incrementer la date
	public static String suivant(int j, int m, int a){
		// incrementation de la date 
		if ((j<28) || (j==28 && m!=2) || ((j==28 && m==2) && (bissextile(a) == true))){
			j++;
		}
		else {
			if (m !=12){
				j = 1;
				m++;
			}
			else {
				j = 1;
				m = 1; 
				a++;
			}
		}
		return dateToString(j,m,a);
	}

}
