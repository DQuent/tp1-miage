package com.acme.mailreader.utils;

import java.util.Comparator;

import com.acme.mailreader.model.Mail;

/**
 * Comparateur de mails
 * 
 * Comme on désire afficher les mails les plus importants en premier, l'element le plus grand retourne une valeur négative
 *
 */
public class MailComparator implements Comparator<Mail> {


	final int MAIL_EGAUX = 0;
	final int MAIL1_PLUS_PETIT  = 1;
	final int MAIL1_PLUS_GRAND = -1;
	
	
	
	public int compare(Mail mail1, Mail mail2) {
		if (unDesMailEstNul(mail1, mail2)) {
			return MAIL_EGAUX;
		}
		if (pasLaMemeImportance(mail1, mail2)) {
			return ordreParImportance(mail1, mail2);
		}
		if (pasLeMemeStatut(mail1, mail2)) {
			return ordreParStatut(mail1, mail2);
		}
		if (pasLeMemeSujet(mail1, mail2)) {
			return ordreParSujet(mail1, mail2);
		}
		return EstDateEgale(mail1, mail2);
	}

	private int ordreParStatut(Mail mail1, Mail mail2) {
		int comp = mail1.getStatut().ordinal()
				- mail2.getStatut().ordinal();
		return comp > 0 ? MAIL1_PLUS_PETIT  : MAIL1_PLUS_GRAND;
	}

	private int ordreParSujet(Mail mail1, Mail mail2) {
		return mail2.getSujet().compareTo(mail1.getSujet());
	}

	private boolean unDesMailEstNul(Mail mail1, Mail mail2) {
		return mail1 == null || mail2 == null;
	}

	private int EstDateEgale(Mail mail1, Mail mail2) {
		return mail2.getDate().compareTo(mail1.getDate());
	}

	private boolean pasLeMemeSujet(Mail mail1, Mail mail2) {
		return mail1.getSujet() != mail2.getSujet();
	}

	private boolean pasLeMemeStatut(Mail mail1, Mail mail2) {
		return mail1.getStatut() != mail2.getStatut();
	}

	private boolean pasLaMemeImportance(Mail mail1, Mail mail2) {
		return mail1.isImportant() != mail2.isImportant();
	}

	private int ordreParImportance(Mail mail1, Mail mail2) {
		if (mail1.isImportant() && !mail2.isImportant()) {
			return MAIL1_PLUS_GRAND;
		} else {
			return MAIL1_PLUS_PETIT;
		}
	}
	

}
