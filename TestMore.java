package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import interfete.Observer;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Test;

import clase.ExpoUtils;
import clase.Expozitie;
import clase.Logger;
import clase.MutableInteger;
import clase.OperaArta;
import clase.OpereArtaFactory;
import clase.Sculptura;
import clase.TipExpozitie;

public class TestMore {

	@AfterClass
	public static void tearDownAfter() {
		System.out.println("2. All TestMore Tests have finished! Weepeey :D");
	}

	@Test
	public void testSingletonLog() {

		Logger log = Logger.getInstance();
		assertNotNull(log);
		Logger log2 = Logger.getInstance();
		assertSame(log, log2);
	}

	@Test
	public void testHashCodeAndEquals() {
		OperaArta opera1 = OpereArtaFactory.creareOperaArta(TipExpozitie.Pictura, "Picasso", "Dama cu camelii", ExpoUtils.changeStringToDate("22-10-1902"));
		OperaArta opera2 = OpereArtaFactory.creareOperaArta(TipExpozitie.Pictura, "Picasso", "Dama cu camelii", ExpoUtils.changeStringToDate("22-10-1902"));
		assertEquals(opera1, opera2);
	}

	@Test
	public void testTipOperaArta() { // Factory
		OperaArta opera = OpereArtaFactory.creareOperaArta(TipExpozitie.Sculptura, "Peter Phoenix", "La umbra Marelui Urs", ExpoUtils.changeStringToDate("24-12-2015"));
		assertTrue(opera instanceof Sculptura);

	}

	@Test
	public void testObserverUpdate() {
		Expozitie expozitie = new Expozitie();

		final MutableInteger mutableInteger = new MutableInteger();

		Observer observer = new Observer() {

			@Override
			public void update(String msj) {
				Logger.getInstance().log(msj);
				mutableInteger.setValue(mutableInteger.getValue() + 1);
			}

		};

		expozitie.adaugaObservator(observer);
		expozitie.addOpera(OpereArtaFactory.creareOperaArta(TipExpozitie.Sculptura, "Vasile Ion", "Tot ce pluteste e o barca", ExpoUtils.changeStringToDate("24-12-2012")));
		assertEquals(mutableInteger.getValue(), 2);// 2 mesaje afisate la adaugarea unei opere
	}

	@Test
	public void testAutorOperaMock() {

		OperaArta opera2 = mock(OperaArta.class);
		when(opera2.getAutor()).thenReturn("Alabala");
		assertTrue(opera2.getAutor().equals("Alabala"));
	}

	@Test
	public void testMockCheckVechime() {
		OperaArta opera = mock(OperaArta.class);
		Date data = ExpoUtils.changeStringToDate("24-12-2012");
		when(opera.checkVechime(data)).thenReturn(true);
		assertTrue(opera.checkVechime(data));
	}
}
