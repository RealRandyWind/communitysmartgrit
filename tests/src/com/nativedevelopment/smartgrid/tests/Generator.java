package com.nativedevelopment.smartgrid.tests;

import com.nativedevelopment.smartgrid.Data;
import com.nativedevelopment.smartgrid.IAction;
import com.nativedevelopment.smartgrid.IData;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

public final class Generator {
	private static Random a_oRandom = new Random();

	private Generator() {

	}

	public static void SetSeed(long iSeed) {
		a_oRandom.setSeed(iSeed);
	}

	public static IData GenerateDataSensor(UUID iSensor, int nTuples) {
		/* create attributes */
		String[] lAttributes = new String[3];
		lAttributes[0] = "timestamp";
		lAttributes[1] = "interval";
		lAttributes[2] = "signal";

		/* initialize bounds */
		int nIntervalUpper = 500;
		int nIntervalLower = 10;
		double dSignalStrength = 30;
		double dSignalBias = 60 * a_oRandom.nextDouble();
		double dSignalBiasNoise = 10;
		double dSignalFrequency = 0.1;
		double dSignalFrequencyNoise = 0.04;

		/* generate actual data */
		Serializable[][] lTuples = new Serializable[nTuples][3];
		for (int iTuple = 0; iTuple < nTuples; ++iTuple) {
			long tBegin = System.currentTimeMillis();
			Interval(a_oRandom.nextInt(nIntervalUpper - nIntervalLower) + nIntervalLower);
			long tEnd =  System.currentTimeMillis();
			long tInterval = tEnd - tBegin;
			double dOmega = 2 * Math.PI * dSignalFrequency + a_oRandom.nextGaussian() * dSignalFrequencyNoise;
			lTuples[iTuple][0] = tBegin;
			lTuples[iTuple][1] = tInterval;
			lTuples[iTuple][2] = a_oRandom.nextGaussian() * dSignalBiasNoise + dSignalBias
					+ dSignalStrength * dOmega * (Math.cos(tEnd * dOmega) - Math.cos(tBegin * dOmega));
		}
		return new Data(iSensor, lTuples, lAttributes);
	}

	public static IData GenerateDataMachine(UUID iMachine, int nTuples) {
		/* create attributes */
		String[] lAttributes = new String[5];
		lAttributes[0] = "timestamp";
		lAttributes[1] = "interval";
		lAttributes[2] = "activity";
		lAttributes[3] = "handled";
		lAttributes[4] = "unhandled";

		/* initialize bounds */
		int nIntervalUpper = 500;
		int nIntervalLower = 10;
		int nMaxLoad = 80;
		double dSignalStrength = 30;
		double dSignalBias = 30 + 40 * a_oRandom.nextDouble();
		double dSignalBiasNoise = 10;
		double dSignalFrequency = 0.1;
		double dSignalFrequencyNoise = 0.04;

		/* generate actual data */
		Serializable[][] lTuples = new Serializable[nTuples][5];
		for (int iTuple = 0; iTuple < nTuples; ++iTuple) {
			long tBegin = System.currentTimeMillis();
			Interval(a_oRandom.nextInt(nIntervalUpper - nIntervalLower) + nIntervalLower);
			long tEnd =  System.currentTimeMillis();
			long tInterval = tEnd - tBegin;
			double dOmega = 2 * Math.PI * dSignalFrequency + a_oRandom.nextGaussian() * dSignalFrequencyNoise;
			lTuples[iTuple][0] = tBegin;
			lTuples[iTuple][1] = tInterval;
			lTuples[iTuple][2] = a_oRandom.nextGaussian() * dSignalBiasNoise + dSignalBias
					+ dSignalStrength * dOmega * (Math.cos(tEnd * dOmega) - Math.cos(tBegin * dOmega));
			lTuples[iTuple][3] = a_oRandom.nextInt(nMaxLoad) * tInterval;
			lTuples[iTuple][4] = a_oRandom.nextInt(nMaxLoad) * tInterval;
		}
		return new Data(iMachine, lTuples, lAttributes);
	}

	public static IData GenerateResult(UUID iSensor, int nTuples) {
		IData oData = null;
		return oData;
	}

	public static IAction GenerateActionSensor() {
		IAction oAction = null;
		return oAction;
	}

	public static IAction GenerateActionMachine() {
		IAction oAction = null;
		return oAction;
	}

	public static IAction GenerateActionServer() {
		IAction oAction = null;
		return oAction;
	}

	public static void Interval(long nLength) {
		try {
			Thread.sleep(nLength);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
