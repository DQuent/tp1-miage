package com.acme.mailreader.bdd;

import static org.junit.Assert.assertThat;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.Is;

import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.domain.Mail.Statut;
import com.acme.mailreader.domain.MailComparator;
import com.acme.mailreader.utils.DateIncorrecteException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Les steps (actions) du test
 * 
 * <p>
 * A noter qu'une nouvelle instance de cette classe est créée à chaque scenario
 * </p>
 *
 */

public class MailComparaisonStep {

	private Mail mail1;
	private Mail mail2;
	private String resultatComparaison;
	Comparator<Mail> comparator = new MailComparator();
	private static final Map<Integer, String> resuAsString = new HashMap<Integer, String>();
	static {
		resuAsString.put(MailComparator.PREMIER_PLUS_PETIT , "MAIL1_APRES");
		resuAsString.put(MailComparator.EGAUX, "EGAUX");
		resuAsString.put(MailComparator.PREMIER_PLUS_GRAND, "MAIL1_AVANT");
	}
	

	@Given("^un premier mail avec l'importance \"([^\"]*)\", le statut \"([^\"]*)\", le sujet \"([^\"]*)\" et la date \"([^\"]*)\"$")
	public void un_premier_mail(boolean importance, Statut statut,
			String sujet, String date) throws DateIncorrecteException {
		//	this.mail1.setStatut(statut);
			this.mail1.setImportant(importance);
			this.mail1.setDate(Instant.parse(date));
			this.mail1.setSujet(sujet);
			
	}

	@Given("^un second mail avec l'importance \"([^\"]*)\", le statut \"([^\"]*)\", le sujet \"([^\"]*)\" et la date \"([^\"]*)\"$")
	public void un_second_mail(boolean importance, Statut statut, String sujet,
			String date) throws DateIncorrecteException {
			this.mail2.setImportant(importance);
			this.mail2.setStatut(statut);
			this.mail2.setDate(Instant.parse(date));
			this.mail2.setSujet(sujet);
	}

	

	@When("^je trie$")
	public void je_trie() throws Throwable {
		this.resultatComparaison =  this.resuAsString.get(this.comparator.compare(mail1, mail2));
	}

	@Then("^le tri d'égalité doit retourner \"([^\"]*)\"$")
	public void le_test_d_egalité(String resu) throws Throwable {
		assertThat(this.resultatComparaison, Is.is(resu));
	}
	

}
