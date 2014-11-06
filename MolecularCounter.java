/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flusim;

/**
 *
 * @author burkesquires
 */
import java.util.*;

public class MolecularCounter {

    private static int[] vRNPcount = new int[8];
    private static int[] vRNAcount = new int[8];
    private static int[] cRNAcount = new int[8];
    private static int[] mRNAcount = new int[8];
    private static int[] proteinCount = new int[10];
    private static int virionCount;
    private static int[] importedvRNPs = new int[8];
    private static int[] virionvRNPs = new int[8];
    private static int[] vRNPExportRNPs = new int[8];
    private static List proteinData0 = new ArrayList();
    private static List proteinData1 = new ArrayList();
    private static List proteinData2 = new ArrayList();
    private static List proteinData3 = new ArrayList();
    private static List proteinData4 = new ArrayList();
    private static List proteinData5 = new ArrayList();
    private static List proteinData6 = new ArrayList();
    private static List proteinData7 = new ArrayList();
    private static List proteinData8 = new ArrayList();
    private static List proteinData9 = new ArrayList();
    private static List vRNPData0 = new ArrayList();
    private static List vRNPData1 = new ArrayList();
    private static List vRNPData2 = new ArrayList();
    private static List vRNPData3 = new ArrayList();
    private static List vRNPData4 = new ArrayList();
    private static List vRNPData5 = new ArrayList();
    private static List vRNPData6 = new ArrayList();
    private static List vRNPData7 = new ArrayList();
    private static List vRNAData0 = new ArrayList();
    private static List vRNAData1 = new ArrayList();
    private static List vRNAData2 = new ArrayList();
    private static List vRNAData3 = new ArrayList();
    private static List vRNAData4 = new ArrayList();
    private static List vRNAData5 = new ArrayList();
    private static List vRNAData6 = new ArrayList();
    private static List vRNAData7 = new ArrayList();
    private static List cRNAData0 = new ArrayList();
    private static List cRNAData1 = new ArrayList();
    private static List cRNAData2 = new ArrayList();
    private static List cRNAData3 = new ArrayList();
    private static List cRNAData4 = new ArrayList();
    private static List cRNAData5 = new ArrayList();
    private static List cRNAData6 = new ArrayList();
    private static List cRNAData7 = new ArrayList();
    private static List mRNAData0 = new ArrayList();
    private static List mRNAData1 = new ArrayList();
    private static List mRNAData2 = new ArrayList();
    private static List mRNAData3 = new ArrayList();
    private static List mRNAData4 = new ArrayList();
    private static List mRNAData5 = new ArrayList();
    private static List mRNAData6 = new ArrayList();
    private static List mRNAData7 = new ArrayList();
    private static List virionData = new ArrayList();
    //Summary lists
    private static int[] precursormRNA = new int[]{44000, 44000};//220000 / 5 (MOI) 44000
    
    public static void removePrecursormRNA(int count) {
        if (count < precursormRNA[0]) {
            precursormRNA[0] = precursormRNA[0] - count;
        } else {
            precursormRNA[0] = 0;
        }
    }

    public static void addPrecursormRNA() {
        precursormRNA[0] = precursormRNA[0] + 1;
    }

    public static double getPrecursormRNARatio() {
        double rate;
        if (precursormRNA[0] == 0) {
            rate = 0;
        } else {
            rate = ((double) precursormRNA[0] / (double) precursormRNA[1]);
        }
        return rate;
    }

    public static void updateImportedvRNPStatus(int vRNPID) {
        importedvRNPs[vRNPID - 1] = 1;
    }

    public static int getImportedvRNPStatus(int vRNPID) {
        return importedvRNPs[vRNPID - 1];
    }

    //vRNP Assembly Counters
    public static void addRNPExportRNPCount(int vRNPID, int Count, double Clock) {
        int counter = vRNPExportRNPs[vRNPID];
        counter = counter + Count;
        vRNPExportRNPs[vRNPID] = counter;
    //MolecularCounter.RemovevRNPCount(vRNPID, Count, Clock);
    }

    public static boolean checkvRNPExportRNPCount() {
        boolean ready = true;

        if (vRNPExportRNPs.length < 1) {
            ready = false;
        }

        return ready;
    }

    public static boolean checkSynthesisProteinCount(int segmentID) {
        boolean ready = true;
        int NPcount = 0;

        switch (segmentID) {
            case 1:
                NPcount = 95;
            case 2:
                NPcount = 95;
            case 3:
                NPcount = 90;
            case 4:
                NPcount = 71;
            case 5:
                NPcount = 62;
            case 6:
                NPcount = 57;
            case 7:
                NPcount = 32;
            case 8:
                NPcount = 29;
        }

        if (proteinCount[0] < 1) {
            ready = false;
        }
        if (proteinCount[1] < 1) {
            ready = false;
        }
        if (proteinCount[2] < 1) {
            ready = false;
        }
        if (proteinCount[4] < NPcount) {
            ready = false;
        }

        if (ready) {
            //virion is ready to bud, remove counts from virion counters
            proteinCount[0] = proteinCount[0] - 1;
            proteinCount[1] = proteinCount[1] - 1;
            proteinCount[2] = proteinCount[2] - 1;
            proteinCount[4] = proteinCount[4] - NPcount;
        }

        return ready;
    }

    public static boolean checkReplicationProteinCount(int segmentID) {
        boolean ready = true;

        int NPcount = 0;

        switch (segmentID) {
            case 1:
                NPcount = 95;
            case 2:
                NPcount = 95;
            case 3:
                NPcount = 90;
            case 4:
                NPcount = 71;
            case 5:
                NPcount = 62;
            case 6:
                NPcount = 57;
            case 7:
                NPcount = 32;
            case 8:
                NPcount = 29;
        }

        if (proteinCount[0] < 1) {
            ready = false;
        }
        if (proteinCount[1] < 1) {
            ready = false;
        }
        if (proteinCount[2] < 1) {
            ready = false;
        }
        if (proteinCount[4] < NPcount) {
            ready = false;
        }

        if (ready) {
            proteinCount[0] = proteinCount[0] - 1;
            proteinCount[1] = proteinCount[1] - 1;
            proteinCount[2] = proteinCount[2] - 1;
            proteinCount[4] = proteinCount[4] - NPcount;
        }

        return ready;
    }

    //Virion Counters
    public static void addVirionvRNPCount(int vRNPID, int Count, double Clock) {
        int counter = virionvRNPs[vRNPID - 1];
        counter = counter + Count;
        virionvRNPs[vRNPID - 1] = counter;
    //MolecularCounter.RemovevRNPCount(vRNPID, Count, Clock);
    }

    public static boolean checkVirionvRNPCount() {
        boolean ready = true;

        for (int i = 0; i < 8; i++) {
            if (vRNPcount[i] < 1) {
                ready = false;
            }
        }
        return ready;
    }

    public static boolean checkVirionProteinCount() {
        boolean ready = true;

        if (proteinCount[0] < 2.2) {
            ready = false;
        }
        if (proteinCount[1] < 2.2) {
            ready = false;
        }
        if (proteinCount[2] < 2.2) {
            ready = false;
        }
        if (proteinCount[3] < 50.0) {
            ready = false;
        }
        if (proteinCount[4] < 1)  {//43.3) {
            ready = false;
        }
        if (proteinCount[5] < 10.0) {
            ready = false;
        }
        if (proteinCount[6] < 300.0) {
            ready = false;
        }
        if (proteinCount[7] < 2.0) {
            ready = false;
        }
        //if (virionProteins[8] < (0 * adjustment[8])) {
        //    ready = false;
        //}
        if (proteinCount[9] < 13.0) {
            ready = false;
        }

        if (ready) {
            //virion is ready to bud, remove counts from virion counters
            proteinCount[0] = proteinCount[0] - (int) 2.2;
            proteinCount[1] = proteinCount[1] - (int) 2.2;
            proteinCount[2] = proteinCount[2] - (int) 2.2;
            proteinCount[3] = proteinCount[3] - (int) 50.0;
            proteinCount[4] = proteinCount[4] - (int) 1;//43.3;
            proteinCount[5] = proteinCount[5] - (int) 10.0;
            proteinCount[6] = proteinCount[6] - (int) 300.0;
            proteinCount[7] = proteinCount[7] - (int) 2.0;
            //virionProteins[8] = virionProteins[8] - (22 * adjustment[8]);
            proteinCount[9] = proteinCount[9] - (int) 13.0;

        }

        return ready;
    }

    //Component counters
    public static void AddvRNPCount(int vRNPID, int Count, double Clock) {
        int counter = vRNPcount[vRNPID - 1];
        counter = counter + Count;
        vRNPcount[vRNPID - 1] = counter;
        MolecularCounter.RecordvRNPCount(vRNPID, counter, Clock);
    }

    public static void RemovevRNPCount(int vRNPID, int Count, double Clock) {
        int counter = vRNPcount[vRNPID - 1];
        counter = counter - Count;
        vRNPcount[vRNPID - 1] = counter;
        MolecularCounter.RecordvRNPCount(vRNPID, counter, Clock);
    }

    public static int GetvRNPCount(int vRNPID) {
        return vRNPcount[vRNPID];
    }

    private static void RecordvRNPCount(int vRNPID, int count, double clock) {
        double[] data = new double[2];

        data[0] = clock;
        data[1] = count;

        switch (vRNPID - 1) {
            case 0:
                vRNPData0.add(data);
                break;
            case 1:
                vRNPData1.add(data);
                break;
            case 2:
                vRNPData2.add(data);
                break;
            case 3:
                vRNPData3.add(data);
                break;
            case 4:
                vRNPData4.add(data);
                break;
            case 5:
                vRNPData5.add(data);
                break;
            case 6:
                vRNPData6.add(data);
                break;
            case 7:
                vRNPData7.add(data);
                break;
        }
    }

    public static void AddvRNACount(int vRNAID, int Count, double Clock) {
        int counter = vRNAcount[vRNAID - 1];
        counter = counter + Count;
        vRNAcount[vRNAID - 1] = counter;
        MolecularCounter.RecordvRNACount(vRNAID, counter, Clock);
    }

    public static void RemovevRNACount(int vRNAID, int Count, double Clock) {
        int counter = vRNAcount[vRNAID - 1];
        counter = counter - Count;
        vRNAcount[vRNAID - 1] = counter;
        MolecularCounter.RecordvRNACount(vRNAID, counter, Clock);
    }

    public static int GetvRNACount(int vRNAID) {
        return vRNAcount[vRNAID - 1];
    }

    private static void RecordvRNACount(int vRNAID, int count, double clock) {
        double[] data = new double[2];

        data[0] = clock;
        data[1] = count;

        switch (vRNAID - 1) {
            case 0:
                vRNAData0.add(data);
                break;
            case 1:
                vRNAData1.add(data);
                break;
            case 2:
                vRNAData2.add(data);
                break;
            case 3:
                vRNAData3.add(data);
                break;
            case 4:
                vRNAData4.add(data);
                break;
            case 5:
                vRNAData5.add(data);
                break;
            case 6:
                vRNAData6.add(data);
                break;
            case 7:
                vRNAData7.add(data);
                break;
        }
    }

    public static void AddcRNACount(int cRNAID, int Count, double Clock) {
        int counter = cRNAcount[cRNAID - 1];
        counter = counter + Count;
        cRNAcount[cRNAID - 1] = counter;
        MolecularCounter.RecordcRNACount(cRNAID, counter, Clock);
    }

    public static void RemovecRNACount(int cRNAID, int Count, double Clock) {
        int counter = cRNAcount[cRNAID - 1];
        counter = counter - Count;
        cRNAcount[cRNAID - 1] = counter;
        MolecularCounter.RecordcRNACount(cRNAID, counter, Clock);
    }

    public static int GetcRNACount(int cRNAID) {
        return cRNAcount[cRNAID - 1];
    }

    private static void RecordcRNACount(int cRNAID, int count, double clock) {
        double[] data = new double[2];

        data[0] = clock;
        data[1] = count;

        switch (cRNAID - 1) {
            case 0:
                cRNAData0.add(data);
                break;
            case 1:
                cRNAData1.add(data);
                break;
            case 2:
                cRNAData2.add(data);
                break;
            case 3:
                cRNAData3.add(data);
                break;
            case 4:
                cRNAData4.add(data);
                break;
            case 5:
                cRNAData5.add(data);
                break;
            case 6:
                cRNAData6.add(data);
                break;
            case 7:
                cRNAData7.add(data);
                break;
        }
    }

    public static void AddmRNACount(int mRNAID, int Count, double Clock) {
        int counter = mRNAcount[mRNAID - 1];
        counter = counter + Count;
        mRNAcount[mRNAID - 1] = counter;
        MolecularCounter.RecordmRNACount(mRNAID, counter, Clock);
    }

    public static void RemovemRNACount(int mRNAID, int Count, double Clock) {
        int counter = mRNAcount[mRNAID - 1];
        if (counter > 0) {
            counter = counter - Count;
            mRNAcount[mRNAID - 1] = counter;
            MolecularCounter.RecordmRNACount(mRNAID, counter, Clock);
        }
    }

    public static int GetmRNACount(int mRNAID) {
        return mRNAcount[mRNAID - 1];
    }

    private static void RecordmRNACount(int mRNAID, int count, double clock) {
        double[] data = new double[2];

        data[0] = clock;
        data[1] = count;

        switch (mRNAID - 1) {
            case 0:
                mRNAData0.add(data);
                break;
            case 1:
                mRNAData1.add(data);
                break;
            case 2:
                mRNAData2.add(data);
                break;
            case 3:
                mRNAData3.add(data);
                break;
            case 4:
                mRNAData4.add(data);
                break;
            case 5:
                mRNAData5.add(data);
                break;
            case 6:
                mRNAData6.add(data);
                break;
            case 7:
                mRNAData7.add(data);
                break;
        }
    }

    public static void AddProteinCount(int ProteinID, int Count, double Clock) {
        int counter = proteinCount[ProteinID - 1];
        counter = counter + Count;
        proteinCount[ProteinID - 1] = counter;
        MolecularCounter.RecordProteinCount(ProteinID, counter, Clock);
    }

    public static void RemoveProteinCount(int ProteinID, int Count, double Clock) {
        int counter = proteinCount[ProteinID - 1];
        counter = counter - Count;
        proteinCount[ProteinID - 1] = counter;
        MolecularCounter.RecordProteinCount(ProteinID, counter, Clock);
    }

    public static int getProteinCount(int proteinID) {
        return proteinCount[proteinID - 1];
    }

    public static double getSynthProteinConc() {

        int pb2 = proteinCount[0];
        if (pb2 < 10) {
            pb2 = 10;
        }

        int pb1 = proteinCount[1];
        if (pb1 < 10) {
            pb1 = 10;
        }

        int pa = proteinCount[2];
        if (pa < 10) {
            pa = 10;
        }

        int np = proteinCount[5];
        if (np < 10) {
            np = 10;
        }

        double temp = (1.0 / (double) Math.log10(pb2));
        temp = temp * (1.0 / (double) Math.log10(pb1));
        temp = temp * (1.0 / (double) Math.log10(pa));
        temp = temp * (1.0 / (double) Math.log10(np));

        return temp;
    }

    public static double getTranscriptionProteinConc() {
        double rate = 0;

        rate = (double) getSynthProteinConc();
        if (getPrecursormRNARatio() == 0) {
            return 0;
        } else {
            return rate / (double) getPrecursormRNARatio();
        }
    }

    public static double getTranscriptConc(int proteinID) {

        int mRNA = mRNAcount[proteinID - 1];
        if (mRNA < 10) {
            mRNA = 10;
        }

        return (double) (1.0 / (double) Math.log10(mRNA));
    }

    public static double getM1ProteinConc() {

        int M1 = proteinCount[7 - 1];
        if (M1 < 10) {
            M1 = 10;
        }

        return (double) (1.0 / (double) Math.log10(M1));
    }

    private static void RecordProteinCount(int proteinID, int count, double clock) {
        double[] data = new double[2];

        data[0] = clock;
        data[1] = count;

        switch (proteinID - 1) {
            case 0:
                proteinData0.add(data);
                break;
            case 1:
                proteinData1.add(data);
                break;
            case 2:
                proteinData2.add(data);
                break;
            case 3:
                proteinData3.add(data);
                break;
            case 4:
                proteinData4.add(data);
                break;
            case 5:
                proteinData5.add(data);
                break;
            case 6:
                proteinData6.add(data);
                break;
            case 7:
                proteinData7.add(data);
                break;
            case 8:
                proteinData8.add(data);
                break;
            case 9:
                proteinData9.add(data);
                break;

        }
    }

    public static void AddVirionCount(int Count, double Clock) {
        int counter = virionCount;
        counter = counter + Count;
        virionCount = counter;
        MolecularCounter.RecordVirionCount(counter, Clock);
    }

    public static void RemoveVirionCount(int Count, double Clock) {
        int counter = virionCount;
        counter = counter - Count;
        virionCount = counter;
        MolecularCounter.RecordVirionCount(counter, Clock);
    }

    private static void RecordVirionCount(int count, double clock) {
        double[] data = new double[2];

        data[0] = clock;
        data[1] = count;

        virionData.add(data);

    }

    public static int GetVirionCount() {
        return virionCount;
    }

    public static void GraphData() {

        GraphmRNAData();
        GraphProteinData();
        GraphcRNAData();
        GraphvRNAData();
        GraphvRNPData();
        GraphVirionData();
        SummarizemRNAData();

    }

    public static void GraphProteinData() {

        List[] data = new List[10];

        data[0] = proteinData0;
        data[1] = proteinData1;
        data[2] = proteinData2;
        data[3] = proteinData3;
        data[4] = proteinData4;
        data[5] = proteinData5;
        data[6] = proteinData6;
        data[7] = proteinData7;
        data[8] = proteinData8;
        data[9] = proteinData9;

        String[] desc = {"PB2", "PB1", "PA", "HA", "NP", "NA", "M1", "M2", "NS1", "NS2"};

        XY_Plotter.PlotXYChart("FluSim Protein Counts", data, desc);

    }

    public static void GraphvRNPData() {

        List[] data = new List[8];

        data[0] = vRNPData0;
        data[1] = vRNPData1;
        data[2] = vRNPData2;
        data[3] = vRNPData3;
        data[4] = vRNPData4;
        data[5] = vRNPData5;
        data[6] = vRNPData6;
        data[7] = vRNPData7;

        String[] desc = {"PB2", "PB1", "PA", "HA", "NP", "NA", "M", "NS"};

        XY_Plotter.PlotXYChart("FluSim vRNP Counts", data, desc);

    }

    public static void GraphvRNAData() {

        List[] data = new List[8];

        data[0] = vRNAData0;
        data[1] = vRNAData1;
        data[2] = vRNAData2;
        data[3] = vRNAData3;
        data[4] = vRNAData4;
        data[5] = vRNAData5;
        data[6] = vRNAData6;
        data[7] = vRNAData7;

        String[] desc = {"PB2", "PB1", "PA", "HA", "NP", "NA", "M", "NS"};

        XY_Plotter.PlotXYChart("FluSim vRNA Counts", data, desc);

    }

    public static void GraphcRNAData() {

        List[] data = new List[8];

        data[0] = cRNAData0;
        data[1] = cRNAData1;
        data[2] = cRNAData2;
        data[3] = cRNAData3;
        data[4] = cRNAData4;
        data[5] = cRNAData5;
        data[6] = cRNAData6;
        data[7] = cRNAData7;

        String[] desc = {"PB2", "PB1", "PA", "HA", "NP", "NA", "M", "NS"};

        XY_Plotter.PlotXYChart("FluSim cRNA Counts", data, desc);

    }

    public static void GraphmRNAData() {

        List[] data = new List[8];

        data[0] = mRNAData0;
        data[1] = mRNAData1;
        data[2] = mRNAData2;
        data[3] = mRNAData3;
        data[4] = mRNAData4;
        data[5] = mRNAData5;
        data[6] = mRNAData6;
        data[7] = mRNAData7;

        String[] desc = {"PB2", "PB1", "PA", "HA", "NP", "NA", "M", "NS"};

        XY_Plotter.PlotXYChart("FluSim mRNA Counts", data, desc);

    }

    public static void GraphVirionData() {

        List[] data = new List[1];

        data[0] = virionData;

        String[] desc = {"Virion"};


        XY_Plotter.PlotXYChart("FluSim Virion Counts", data, desc);

    }

    public static void SummarizemRNAData() {

        HashMap mRNASummaryData = new HashMap();
        HashMap cRNASummaryData = new HashMap();
        HashMap vRNASummaryData = new HashMap();
        List[] list = new List[3];

        ArrayList mRNAarrays = new ArrayList();
        mRNAarrays.add(mRNAData0);
        mRNAarrays.add(mRNAData1);
        mRNAarrays.add(mRNAData2);
        mRNAarrays.add(mRNAData3);
        mRNAarrays.add(mRNAData4);
        mRNAarrays.add(mRNAData5);
        mRNAarrays.add(mRNAData6);
        mRNAarrays.add(mRNAData7);


        int parentcount = mRNAarrays.size();

        //Loop though each of the 8 segments
        
        for (int i = 0; i < parentcount; i++) {

            ArrayList array = (ArrayList) mRNAarrays.get(i);

            int childcount = array.size();

            if (childcount > 0) {

                for (int j = 0; j < childcount; j++) {

                    double[] data = new double[2];
                    data = (double[]) array.get(j);

                    double clock = data[0];
                    int value = (int) data[1];

                    int time = (int) Math.floor(clock);

                    if (mRNASummaryData.containsKey(Math.floor(clock))) {

                        int temp = (Integer) mRNASummaryData.get(Math.floor(clock));

                        mRNASummaryData.put(time, (temp + value));

                    } else {

                        mRNASummaryData.put(time, value);

                    }
                }
            }
        }

        ArrayList cRNAarrays = new ArrayList();
        cRNAarrays.add(cRNAData0);
        cRNAarrays.add(cRNAData1);
        cRNAarrays.add(cRNAData2);
        cRNAarrays.add(cRNAData3);
        cRNAarrays.add(cRNAData4);
        cRNAarrays.add(cRNAData5);
        cRNAarrays.add(cRNAData6);
        cRNAarrays.add(cRNAData7);


        parentcount = cRNAarrays.size();

        for (int i = 0; i < parentcount; i++) {

            ArrayList array = (ArrayList) cRNAarrays.get(i);

            int childcount = array.size();

            if (childcount > 0) {

                for (int j = 0; j < childcount; j++) {


                    double[] data = new double[2];
                    data = (double[]) array.get(j);

                    double clock = data[0];
                    int value = (int) data[1];

                    int time = (int) Math.floor(clock);

                    if (cRNASummaryData.containsKey(Math.floor(clock))) {

                        int temp = (Integer) cRNASummaryData.get(Math.floor(clock));
                        cRNASummaryData.put(time, (temp + value));

                    } else {

                        cRNASummaryData.put(time, value);

                    }
                }
            }
        }

        ArrayList vRNAarrays = new ArrayList();
        vRNAarrays.add(vRNAData0);
        vRNAarrays.add(vRNAData1);
        vRNAarrays.add(vRNAData2);
        vRNAarrays.add(vRNAData3);
        vRNAarrays.add(vRNAData4);
        vRNAarrays.add(vRNAData5);
        vRNAarrays.add(vRNAData6);
        vRNAarrays.add(vRNAData7);
        //vRNPs
        vRNAarrays.add(vRNPData0);
        vRNAarrays.add(vRNPData1);
        vRNAarrays.add(vRNPData2);
        vRNAarrays.add(vRNPData3);
        vRNAarrays.add(vRNPData4);
        vRNAarrays.add(vRNPData5);
        vRNAarrays.add(vRNPData6);
        vRNAarrays.add(vRNPData7);


        parentcount = vRNAarrays.size();

        for (int i = 0; i < parentcount; i++) {

            ArrayList array = (ArrayList) vRNAarrays.get(i);

            int childcount = array.size();

            if (childcount > 0) {

                for (int j = 0; j < childcount; j++) {

                    double[] data = new double[2];
                    data = (double[]) array.get(j);

                    double clock = data[0];
                    int value = (int) data[1];

                    int time = (int) Math.floor(clock);

                    if (vRNASummaryData.containsKey(Math.floor(clock))) {

                        int temp = (Integer) vRNASummaryData.get(Math.floor(clock));
                        vRNASummaryData.put(time, (temp + value));

                    } else {

                        vRNASummaryData.put(time, value);

                    }
                }
            }
        }

        list[0] = HashMapToArrayList(mRNASummaryData);
        list[1] = HashMapToArrayList(cRNASummaryData);
        list[2] = HashMapToArrayList(vRNASummaryData);

        String[] desc = {"mRNA", "cRNA", "vRNA"};

        XY_Plotter.PlotXYChart("FluSim Summary Counts", list, desc);

    }

    public static ArrayList HashMapToArrayList(HashMap Map) {

        HashMap map = new HashMap();
        map = Map;

        ArrayList AL = new ArrayList();

        Iterator iterator = map.keySet().iterator();

        while (iterator.hasNext()) {

            int mapkey = (Integer) iterator.next();
            int mapvalue = (Integer) map.get(mapkey);

            double[] data = new double[2];
            data[0] = mapkey;
            data[1] = mapvalue;

            AL.add(data);

        }

        return AL;

    }
}





