package com.acme.mailreader.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import com.acme.mailreader.domain.Mail.Statut;
import com.acme.mailreader.utils.DateIncorrecteException;

public class MailComparatorTest {
	
	private MailComparator comparator;

	@Before
	public void setUp() throws Exception {
		this.comparator = new MailComparator();
	}

	@Test
	public final void egauxSiDeuxMailsNuls() {
		Mail mail1 = null;
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	@Test
	public final void egauxSiUnDesMailsNuls() {
		Mail mail1 = new Mail();
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	//Autres tests unitaires
	
	@Test
	public final void egauxSiToutLesChampsDesMailsEgaux() throws DateIncorrecteException {
		Instant i = Instant.now();
		Mail mail1 = new Mail();
		mail1.setDate(i);
		mail1.setImportant(true);
		mail1.setStatut(Statut.SENT);
		mail1.setSujet("aaa");
			
		Mail mail2 = new Mail();
		mail2.setDate(i);
		mail2.setImportant(true);
		mail2.setStatut(Statut.SENT);
		mail2.setSujet("aaa");
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	@Test
	public final void nonEgauxSiImportanceDifferente() throws DateIncorrecteException {
		Instant i = Instant.now();
		Mail mail1 = new Mail();
		mail1.setDate(i);
		mail1.setImportant(true);
		mail1.setStatut(Statut.SENT);
		mail1.setSujet("aaa");
			
		Mail mail2 = new Mail();
		mail2.setDate(i);
		mail2.setImportant(false);
		mail2.setStatut(Statut.SENT);
		mail2.setSujet("aaa");
		assertThat(comparator.compare(mail1, mail2), is(-1));
	}
	
	@Test
	public final void nonEgauxSiStatutDifferent() throws DateIncorrecteException {
		Instant i = Instant.now();
		Mail mail1 = new Mail();
		mail1.setDate(i);
		mail1.setImportant(false);
		mail1.setStatut(Statut.SENT);
		mail1.setSujet("aaa");
			
		Mail mail2 = new Mail();
		mail2.setDate(i);
		mail2.setImportant(false);
		mail2.setStatut(Statut.UNSENT);
		mail2.setSujet("aaa");
		assertThat(comparator.compare(mail1, mail2), is(-1));
	}
	
	@Test
	public final void nonEgauxSiSujetDifferent() throws DateIncorrecteException {
		Instant i = Instant.now();
		Mail mail1 = new Mail();
		mail1.setDate(i);
		mail1.setImportant(false);
		mail1.setStatut(Statut.SENT);
		mail1.setSujet("aaa");
			
		Mail mail2 = new Mail();
		mail2.setDate(i);
		mail2.setImportant(false);
		mail2.setStatut(Statut.SENT);
		mail2.setSujet("aaab");
		assertThat(comparator.compare(mail1, mail2), is(-1));
	}
	
	@Test
	public final void nonEgauxEtMail2estPremier() throws DateIncorrecteException {
		Instant i = Instant.now();
		Mail mail1 = new Mail();
		mail1.setDate(i);
		mail1.setImportant(false);
		mail1.setStatut(Statut.READ);
		mail1.setSujet("aaa");
			
		Mail mail2 = new Mail();
		mail2.setDate(i);
		mail2.setImportant(true);
		mail2.setStatut(Statut.READ);
		mail2.setSujet("aaa");
		assertThat(comparator.compare(mail1, mail2), is(1));
	}
	
	
	
	
}
