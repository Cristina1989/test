package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clase.ExpoUtils;
import clase.Expozitie;
import clase.OperaArta;
import clase.OpereArtaFactory;
import clase.TipExpozitie;

public class TestExpo {

	private static Expozitie expo;

	@BeforeClass
	public static void onlyOnce() throws Exception {
		expo = new Expozitie();
		ExpoUtils.addFromFile(expo);
		// System.out.println("O singura data!");
	}

	@AfterClass
	public static void tearDownAfter() {
		System.out.println("1. All TestExpo Tests have finished! Yay :D");
	}

	@Before
	public void initialize() throws Exception {

		/*
		 * expo = new Expozitie(); ExpoUtils.addFromFile(expo); System.out.println("De 10 ori!");
		 */
	}

	@After
	public void tearDown() {

		// System.out.println("Another test finished! ");

	}

	@Test
	public void testDateFisierTipExpo() {

		assertTrue(expo.getOpere().contains(OpereArtaFactory.creareOperaArta(TipExpozitie.Pictura, "Picasso", "Batranul Chitarist", ExpoUtils.changeStringToDate("21-09-1903"))));

	}

	@Test
	public void testArtListAfterYear() {

		List<OperaArta> opereRecente = ExpoUtils.getArtListAfterYear(expo.getOpere(), 1984);
		assertEquals(opereRecente.size(), 4);
	}

	@Test
	public void testArtListAfterYearNotNull() {

		List<OperaArta> opereRecente = ExpoUtils.getArtListAfterYear(expo.getOpere(), 1984);
		assertNotNull(opereRecente);
	}

	@Test
	public void testGetAutorOpere() {

		Map<String, List<OperaArta>> autoriOpere = ExpoUtils.getAuthorArtMap(expo);

		assertEquals(autoriOpere.get("Picasso").size(), 2);
	}

	@Test
	public void testGetAutorOpereNull() {

		Map<String, List<OperaArta>> autoriOpere = ExpoUtils.getAuthorArtMap(expo);

		assertNull(autoriOpere.get("Anonim"));
	}

	@Test
	public void testGetAutorTipExpo() {

		Map<String, List<TipExpozitie>> autoriTipExpo = ExpoUtils.getAuthorTypeExpo(expo);

		assertTrue(autoriTipExpo.get("Pomello").contains(TipExpozitie.Pictura));
		assertTrue(autoriTipExpo.get("Pomello").contains(TipExpozitie.Sculptura));

	}

	@Test
	public void testGetNewestArt() {

		assertTrue(ExpoUtils.getNewestArtYear(expo.getOpere()) == 1997);
	}

	@Test
	public void testGetOldestArt() {

		assertFalse(ExpoUtils.getOldestArtYear(expo.getOpere()) != 1486);
	}

	@Test
	public void testGetDifferenceMaxMinYear() {

		assertEquals(ExpoUtils.getDifferenceMaxMinYear(expo.getOpere()), 511);
	}

	@Test
	public void testCheckVechime() {
		Date data = ExpoUtils.changeStringToDate("24-12-1949");
		OperaArta opera = OpereArtaFactory.creareOperaArta(TipExpozitie.Pictura, "Pomello", "Batranul Chitarist", data);

		assertTrue(opera.checkVechime(data));
	}

}
