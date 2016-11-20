package GestionLog;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

//formateur pour l'affichage du fichier log
public class MyFormatter extends Formatter{
	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	// formatage d'un enregistrement
	public String format(LogRecord record) {
		StringBuffer s = new StringBuffer(1000);
		Timestamp stamp = new Timestamp(record.getMillis());
		Date date = new Date(stamp.getTime());
     
		String formattedDate = formatter.format(date);
     
		s.append(formattedDate+"-->"+record.getLevel()+"["+formatMessage(record)+"]\n");
		return s.toString();
	}

	// entête du fichier de log
	public String getHead(Handler h) {     
		String formattedDate = formatter.format(new Date());
		return formattedDate+"-->"+"Ouverture du fichier de log\n";
	}	

	// fin du fichier de log
	public String getTail(Handler h) {
		String formattedDate = formatter.format(new Date());
		return formattedDate+"-->"+"Fermeture du fichier de log\n";
	}
	
}
