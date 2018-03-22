package com.acme.mailreader.domain;

import static org.junit.Assert.assertThat;

import java.time.Instant;
import org.hamcrest.core.IsNot;

import org.hamcrest.core.Is;
import org.junit.Ignore;
import org.junit.Test;

import com.acme.mailreader.utils.DateIncorrecteException;

public class MailTest {
	
	private MailComparator comparator;

	@Test(expected=DateIncorrecteException.class)
	public final void erreurSiDateAvant1979() throws DateIncorrecteException {
		Mail mail1 = new Mail();
		mail1.setDate(Instant.MIN);
		Mail mail2 = new Mail();
		mail1.setDate(Instant.now());
		assertThat(comparator.compare(mail1,mail2), Is.is(0));
				
	}

}
