package flusim;

/* FluSim.java
 * Created on April 3, 2006
 * @author Burke Squires, richard.squires@utsouthwestern.edu; burkesquires@gmail.com
 * Tags: 6 digit number; first 3 digits 100 + proccess ID; last 3 digits: port (1) and event or molecule (2)
 * e.g. 108101 -
 */
import eduni.simjava.*;
import eduni.simjava.distributions.*;
//import java.util.*;
//import java.io.*;
import java.util.ArrayList;

public class FluSim {

    public static void main(String[] args) {

        /********************************************************************************/
        double DebugSimRunTime = 10000.0;      //Time for simulation based upon 84600 seconds in a day

        //Sim_system.set_seed(10091);

        Sim_system.initialise();
        Binding binding = new Binding("Binding");
        Internalization internalization = new Internalization("Internalization");
        Actin_dependent_transport actin_dependent_transport = new Actin_dependent_transport("Actin_dependent_transport");
        Dynein_directed_transport dynein_directed_transport = new Dynein_directed_transport("Dynein_directed_transport");
        End_directed_transport end_directed_transport = new End_directed_transport("End_directed_transport");
        Fusion fusion = new Fusion("Fusion");
        Uncoating Uncoating = new Uncoating("Uncoating");
        RNP_nuclear_import RNP_nuclear_import = new RNP_nuclear_import("RNP_nuclear_import");
        cRNA_synthesis cRNA_synthesis = new cRNA_synthesis("cRNA_synthesis");
        mRNA_transcription mRNA_transcription = new mRNA_transcription("mRNA_transcription");
        Viral_protein_translation Viral_protein_translation = new Viral_protein_translation("Viral_protein_translation");
        Viral_protein_import Viral_protein_import = new Viral_protein_import("Viral_protein_import");
        vRNA_replication vRNA_replication = new vRNA_replication("vRNA_replication");
        RNP_assembly RNP_assembly = new RNP_assembly("RNP_assembly");
        RNP_nuclear_export RNP_nuclear_export = new RNP_nuclear_export("RNP_nuclear_export");
        M1_translocation M1_translocation = new M1_translocation("M1_translocation");
        Protein_secretion Protein_secretion = new Protein_secretion("Protein_secretion");
        Virion_assembly Virion_assembly = new Virion_assembly("Virion_assembly");
        Virion_release Virion_release = new Virion_release("Virion_release");


        /********************************************************************************/
        // Link the entities' ports
        Sim_system.link_ports("Binding", "to_internalization", "Internalization", "In");
        Sim_system.link_ports("Internalization", "to_actin_dependent_transport", "Actin_dependent_transport", "In");
        Sim_system.link_ports("Actin_dependent_transport", "to_Dynein_directed_transport", "Dynein_directed_transport", "In");
        Sim_system.link_ports("Dynein_directed_transport", "to_End_directed_transport", "End_directed_transport", "In");
        Sim_system.link_ports("End_directed_transport", "to_fusion", "Fusion", "In");
        Sim_system.link_ports("Fusion", "to_uncoating", "Uncoating", "In");
        Sim_system.link_ports("Uncoating", "to_RNP_nuclear_import", "RNP_nuclear_import", "In");

        Sim_system.link_ports("RNP_nuclear_import", "to_mRNA_transcription", "mRNA_transcription", "In");
        Sim_system.link_ports("RNP_nuclear_import", "to_cRNA_synthesis", "cRNA_synthesis", "In");

        Sim_system.link_ports("cRNA_synthesis", "to_vRNA_replication", "vRNA_replication", "In");
        Sim_system.link_ports("cRNA_synthesis", "to_cRNA_synthesis", "cRNA_synthesis", "In");

        Sim_system.link_ports("mRNA_transcription", "to_Viral_protein_translation", "Viral_protein_translation", "In");
        Sim_system.link_ports("mRNA_transcription", "to_mRNA_transcription", "mRNA_transcription", "In");

        Sim_system.link_ports("Viral_protein_translation", "to_Protein_secretion", "Protein_secretion", "In");
        Sim_system.link_ports("Viral_protein_translation", "to_M1_translocation", "M1_translocation", "In");
        Sim_system.link_ports("Viral_protein_translation", "to_Viral_protein_import", "Viral_protein_import", "In");
        Sim_system.link_ports("Viral_protein_translation", "to_Viral_protein_translation", "Viral_protein_translation", "In");

        Sim_system.link_ports("Viral_protein_import", "to_mRNA_transcription", "mRNA_transcription", "In");
        Sim_system.link_ports("Viral_protein_import", "to_cRNA_synthesis", "cRNA_synthesis", "In");
        Sim_system.link_ports("Viral_protein_import", "to_vRNA_replication", "vRNA_replication", "In");
        Sim_system.link_ports("Viral_protein_import", "to_RNP_nuclear_export", "RNP_nuclear_export", "In");

        Sim_system.link_ports("vRNA_replication", "to_cRNA_synthesis", "cRNA_synthesis", "In");
        Sim_system.link_ports("vRNA_replication", "to_RNP_assembly", "RNP_assembly", "In");
        Sim_system.link_ports("vRNA_replication", "to_mRNA_transcription", "mRNA_transcription", "In");
        Sim_system.link_ports("vRNA_replication", "to_vRNA_replication", "vRNA_replication", "In");

        Sim_system.link_ports("RNP_assembly", "to_RNP_nuclear_export", "RNP_nuclear_export", "In");
        Sim_system.link_ports("RNP_nuclear_export", "to_Virion_assembly", "Virion_assembly", "In");
        Sim_system.link_ports("M1_translocation", "to_Virion_assembly", "Virion_assembly", "In");
        Sim_system.link_ports("Protein_secretion", "to_Virion_assembly", "Virion_assembly", "In");
        Sim_system.link_ports("Virion_assembly", "to_Virion_release", "Virion_release", "In");

        Sim_system.set_trace_detail(false, false, false);

        Sim_system.set_termination_condition(Sim_system.TIME_ELAPSED, DebugSimRunTime, false);
        //Sim_system.set_termination_condition(Sim_system.EVENTS_COMPLETED, "Virion_assembly", 118101, 1, true);

        //Sim_system.set_output_analysis(Sim_system.IND_REPLICATIONS, 2, 0.5);
        Sim_system.set_report_detail(false, false);
        //Sim_system.generate_graphs("FluSim_graphs");
        //Sim_system.generate_molecular_graphs("FluSim_mol_graphs");
        Sim_system.run();
        MolecularCounter.GraphData();
        System.out.println("End of simulation!");

    }
}

class Binding extends Sim_entity {

    private Sim_port to_internalization;
    private Sim_negexp_obj proc;

    Binding(String name) {
        super(name);

        to_internalization = new Sim_port("to_internalization");
        add_port(to_internalization);

        proc = new Sim_negexp_obj("Processing", 1);
        add_generator(proc);

    }

    @Override
    public void body() {

        System.out.println("Binding: 101101 at " + Sim_system.sim_clock());

        sim_schedule(to_internalization, proc.sample(), 101101, 1);
    //sim_trace(1, "Binding: 101101");

    }
}

class Internalization extends Sim_entity {

    private Sim_port in;
    private Sim_port to_actin_dependent_transport;
    private Sim_negexp_obj proc;

    Internalization(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);

        to_actin_dependent_transport = new Sim_port("to_actin_dependent_transport");
        add_port(to_actin_dependent_transport);

        proc = new Sim_negexp_obj("Processing", 6);
        add_generator(proc);

    }

    @Override
    public void body() {

        Sim_event e = new Sim_event();
        sim_get_next(new Sim_type_p(101101), e);

        sim_completed(e);

        System.out.println("Internalization: " + e.get_tag() + " at " + Sim_system.sim_clock());

        sim_schedule(to_actin_dependent_transport, 54 + proc.sample(), 102101, 1);
    //sim_trace(1, "Internalization: " + e.get_src() + " from " + e.get_tag() + " at " + Sim_system.sim_clock());

    }
}

class Actin_dependent_transport extends Sim_entity {

    private Sim_port in;
    private Sim_port to_Dynein_directed_transport;
    private Sim_negexp_obj proc;

    Actin_dependent_transport(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);

        to_Dynein_directed_transport = new Sim_port("to_Dynein_directed_transport");
        add_port(to_Dynein_directed_transport);

        proc = new Sim_negexp_obj("Processing", 24);
        add_generator(proc);
    }

    @Override
    public void body() {

        Sim_event e = new Sim_event();
        sim_get_next(new Sim_type_p(102101), e);

        sim_completed(e);

        System.out.println("Actin-dependent transport: " + e.get_tag() + " at " + Sim_system.sim_clock());

        sim_schedule(to_Dynein_directed_transport, 226 + proc.sample(), 103101);
    //sim_trace(1, "Actin-dependent transport:  " + e.get_src() + " from " + e.get_tag() + " at " + Sim_system.sim_clock());
    }
}

class Dynein_directed_transport extends Sim_entity {

    private Sim_port in;
    private Sim_port to_End_directed_transport;
    private Sim_negexp_obj proc;

    Dynein_directed_transport(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);

        to_End_directed_transport = new Sim_port("to_End_directed_transport");
        add_port(to_End_directed_transport);

        proc = new Sim_negexp_obj("Processing", 1);
        add_generator(proc);

    }

    @Override
    public void body() {

        Sim_event e = new Sim_event();
        sim_get_next(new Sim_type_p(103101), e);

        sim_completed(e);

        System.out.println("Dynein_directed_transport: " + e.get_tag() + " at " + Sim_system.sim_clock());

        sim_schedule(to_End_directed_transport, 9 + proc.sample(), 104101);
    //sim_trace(1, "Dynein_directed_transport: " + e.get_src() + " from " + e.get_tag() + " at " + Sim_system.sim_clock());
    }
}

class End_directed_transport extends Sim_entity {

    private Sim_port in;
    private Sim_port to_fusion;
    private Sim_negexp_obj proc;

    End_directed_transport(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);

        to_fusion = new Sim_port("to_fusion");
        add_port(to_fusion);

        proc = new Sim_negexp_obj("Processing", 30);
        add_generator(proc);
    }

    @Override
    public void body() {

        Sim_event e = new Sim_event();
        sim_get_next(new Sim_type_p(104101), e);

        sim_completed(e);

        System.out.println("Late endosome: " + e.get_tag() + " at " + Sim_system.sim_clock());

        sim_schedule(to_fusion, 270 + proc.sample(), 105101);
    //sim_trace(1, "Late endosome: " + e.get_src() + " from " + e.get_tag() + " at " + Sim_system.sim_clock());
    }
}

class Fusion extends Sim_entity {

    private Sim_port in;
    private Sim_port to_uncoating;
    private Sim_negexp_obj proc;
    private Sim_random_obj prob;

    Fusion(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);

        to_uncoating = new Sim_port("to_uncoating");
        add_port(to_uncoating);

        proc = new Sim_negexp_obj("Processing", 120);
        add_generator(proc);


        prob = new Sim_random_obj("Probability");
        add_generator(prob);
    }

    @Override
    public void body() {

        while (Sim_system.running()) {

            Sim_event e = new Sim_event();
            sim_get_next(new Sim_type_p(105101), e);
            sim_completed(e);

            System.out.println("Fusion: " + e.get_tag() + " at " + Sim_system.sim_clock());

            sim_schedule(to_uncoating, 1080 + proc.sample(), 106101);

        //sim_trace(1, "Fusion: " + e.get_src() + " from " + e.get_tag() + " at " + Sim_system.sim_clock());
        }
    }
}

class Uncoating extends Sim_entity {

    private Sim_port in;
    private Sim_port to_RNP_nuclear_import;
    private Sim_negexp_obj proc;
    private Sim_random_obj prob;

    Uncoating(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);
        to_RNP_nuclear_import = new Sim_port("to_RNP_nuclear_import");
        add_port(to_RNP_nuclear_import);

        proc = new Sim_negexp_obj("Processing", 60);
        add_generator(proc);

        prob = new Sim_random_obj("Probability");
        add_generator(prob);
    }

    @Override
    public void body() {

        while (Sim_system.running()) {

            Sim_event e = new Sim_event();
            int[] tags = {106101};
            sim_get_next(new Sim_type_p(tags), e);

            sim_completed(e);

            System.out.println("Uncoating: " + e.get_tag() + " at " + Sim_system.sim_clock());

            for (int vRNPID = 1; vRNPID < 9; vRNPID++) {
                sim_schedule(to_RNP_nuclear_import, 540 + proc.sample(), (107100 + vRNPID));
            }

        }
    }
}

class RNP_nuclear_import extends Sim_entity {

    private Sim_port in;
    private Sim_port to_mRNA_transcription;
    private Sim_port to_cRNA_synthesis;
    private Sim_negexp_obj proc;
    private int[] poly = new int[]{29, 29, 27, 22, 19, 17, 12, 11};

    RNP_nuclear_import(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);
        to_mRNA_transcription = new Sim_port("to_mRNA_transcription");
        add_port(to_mRNA_transcription);

        to_cRNA_synthesis = new Sim_port("to_cRNA_synthesis");
        add_port(to_cRNA_synthesis);

        proc = new Sim_negexp_obj("Processing", 60);
        add_generator(proc);
    }

    @Override
    public void body() {

        while (Sim_system.running()) {

            Sim_event e = new Sim_event();
            int[] tags = {
                107101, 107102, 107103, 107104, 107105, 107106, 107107, 107108
            };
            sim_get_next(new Sim_type_p(tags), e);

            if (e.get_tag() != -1) {

                int tag = e.get_tag();
                int vRNPID = tag % 10;

                sim_completed(e);

                System.out.println("RNP Import: " + tag + " at " + Sim_system.sim_clock());

                sim_schedule(to_mRNA_transcription, 540 + proc.sample(), (108200 + vRNPID));
                MolecularCounter.AddmRNACount(vRNPID, poly[vRNPID - 1], Sim_system.sim_clock());

                sim_schedule(to_cRNA_synthesis, 540 + proc.sample(), 108100 + vRNPID);
            }
        }
    }
}

class cRNA_synthesis extends Sim_entity {

    private Sim_port in;
    private Sim_port to_vRNA_replication;
    private Sim_port to_cRNA_synthesis;
    private Sim_negexp_obj cRNA1;
    private Sim_negexp_obj cRNA2;
    private Sim_negexp_obj cRNA3;
    private Sim_negexp_obj cRNA4;
    private Sim_negexp_obj cRNA5;
    private Sim_negexp_obj cRNA6;
    private Sim_negexp_obj cRNA7;
    private Sim_negexp_obj cRNA8;
    private Sim_random_obj prob;
    private int[] poly = new int[]{29, 29, 27, 22, 19, 17, 12, 11};
    private double time;
    double initiation = 46.0;

    cRNA_synthesis(String name) {
        super(name);

        //Ports
        in = new Sim_port("In");
        add_port(in);
        to_vRNA_replication = new Sim_port("to_vRNA_replication");
        add_port(to_vRNA_replication);
        to_cRNA_synthesis = new Sim_port("to_cRNA_synthesis");
        add_port(to_cRNA_synthesis);

        //Distributions
        cRNA1 = new Sim_negexp_obj("cRNA1", initiation + 47);
        add_generator(cRNA1);
        cRNA2 = new Sim_negexp_obj("cRNA2", initiation + 47);
        add_generator(cRNA2);
        cRNA3 = new Sim_negexp_obj("cRNA3", initiation + 45);
        add_generator(cRNA3);
        cRNA4 = new Sim_negexp_obj("cRNA4", initiation + 36);
        add_generator(cRNA4);
        cRNA5 = new Sim_negexp_obj("cRNA5", initiation + 31);
        add_generator(cRNA5);
        cRNA6 = new Sim_negexp_obj("cRNA6", initiation + 28);
        add_generator(cRNA6);
        cRNA7 = new Sim_negexp_obj("cRNA7", initiation + 21);
        add_generator(cRNA7);
        cRNA8 = new Sim_negexp_obj("cRNA8", initiation + 18);
        add_generator(cRNA8);

        prob = new Sim_random_obj("Probability");
        add_generator(prob);
    }

    @Override
    public void body() {
        while (Sim_system.running()) {

            Sim_event e = new Sim_event();
            int[] tags = {
                108101, 108102, 108103, 108104, 108105, 108106, 108107, 108108,
                112101, 112102, 112103, 112104, 112105, 112106, 112107, 112108,
                113101, 113102, 113103, 113104, 113105, 113106, 113107, 113108
            };
            sim_get_next(new Sim_type_p(tags), e);

            int tag = e.get_tag();

            if (e.get_tag() != -1) {

                int cRNAID = tag % 10;
                boolean ready = false;

                if (((tag >= 108101) && (tag <= 108108)) | ((tag >= 113101) && (tag <= 113108))) {
                    ready = MolecularCounter.checkSynthesisProteinCount(cRNAID);
                }

                sim_completed(e);


                double concentrations = MolecularCounter.getSynthProteinConc();

                switch (cRNAID) {
                    case 1:
                        cRNA1 = new Sim_negexp_obj("cRNA", (initiation + 47) * concentrations);
                        time = cRNA1.sample();
                        break;
                    case 2:
                        cRNA2 = new Sim_negexp_obj("cRNA", (initiation + 47) * concentrations);
                        time = cRNA2.sample();
                        break;
                    case 3:
                        cRNA3 = new Sim_negexp_obj("cRNA", (initiation + 45) * concentrations);
                        time = cRNA3.sample();
                        break;
                    case 4:
                        cRNA4 = new Sim_negexp_obj("cRNA", (initiation + 36) * concentrations);
                        time = cRNA4.sample();
                        break;
                    case 5:
                        cRNA5 = new Sim_negexp_obj("cRNA", (initiation + 31) * concentrations);
                        time = cRNA5.sample();
                        break;
                    case 6:
                        cRNA6 = new Sim_negexp_obj("cRNA", (initiation + 28) * concentrations);
                        time = cRNA6.sample();
                        break;
                    case 7:
                        cRNA7 = new Sim_negexp_obj("cRNA", (initiation + 21) * concentrations);
                        time = cRNA7.sample();
                        break;
                    case 8:
                        cRNA8 = new Sim_negexp_obj("cRNA", (initiation + 18) * concentrations);
                        time = cRNA8.sample();
                        break;
                    }
                if (ready) {

                    sim_schedule(to_vRNA_replication, time, 109100 + cRNAID);
                    MolecularCounter.AddcRNACount(cRNAID, poly[cRNAID - 1], Sim_system.sim_clock());
                    System.out.println("cRNA synthesis " + tag + " at " + Sim_system.sim_clock());

                //sim_trace(1, "cRNA synthesis: " + e.get_src() + " from " + tag + " at " + Sim_system.sim_clock());
                }

                if (prob.sample() < 0.00012) {
                    if (MolecularCounter.GetcRNACount(cRNAID) > poly[cRNAID - 1]) {
                        MolecularCounter.RemovecRNACount(cRNAID, poly[cRNAID - 1], Sim_system.sim_clock());
                    }
                }
                if (((tag >= 108101) && (tag <= 108108)) | ((tag >= 113101) && (tag <= 113108))) {
                    sim_schedule(to_cRNA_synthesis, time, e.get_tag());
                }
            }
        }
    }
}

class mRNA_transcription extends Sim_entity {

    private Sim_port in;
    private Sim_port to_Viral_protein_translation;
    private Sim_port to_mRNA_transcription;
    private Sim_negexp_obj mRNA1;
    private Sim_negexp_obj mRNA2;
    private Sim_negexp_obj mRNA3;
    private Sim_negexp_obj mRNA4;
    private Sim_negexp_obj mRNA5;
    private Sim_negexp_obj mRNA6;
    private Sim_negexp_obj mRNA7;
    private Sim_negexp_obj mRNA8;
    private Sim_random_obj prob;
    private int[] poly = new int[]{29, 29, 27, 22, 19, 17, 12, 11};
    double time;
    double initiation = 46.0 * 10;

    mRNA_transcription(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);

        //Ports
        to_Viral_protein_translation = new Sim_port("to_Viral_protein_translation");
        add_port(to_Viral_protein_translation);
        to_mRNA_transcription = new Sim_port("to_mRNA_transcription");
        add_port(to_mRNA_transcription);

        //Distributions
        prob = new Sim_random_obj("Probability");
        add_generator(prob);

        mRNA1 = new Sim_negexp_obj("mRNA", initiation + 47);
        add_generator(mRNA1);
        mRNA2 = new Sim_negexp_obj("mRNA", initiation + 47);
        add_generator(mRNA2);
        mRNA3 = new Sim_negexp_obj("mRNA", initiation + 45);
        add_generator(mRNA3);
        mRNA4 = new Sim_negexp_obj("mRNA", initiation + 36);
        add_generator(mRNA4);
        mRNA5 = new Sim_negexp_obj("mRNA", initiation + 31);
        add_generator(mRNA5);
        mRNA6 = new Sim_negexp_obj("mRNA", initiation + 28);
        add_generator(mRNA6);
        mRNA7 = new Sim_negexp_obj("mRNA", initiation + 21);
        add_generator(mRNA7);
        mRNA8 = new Sim_negexp_obj("mRNA", initiation + 18);
        add_generator(mRNA8);
    }

    @Override
    public void body() {

        while (Sim_system.running()) {

            Sim_event e = new Sim_event();
            int[] tags = {
                108201, 108202, 108203, 108204, 108205, 108206, 108207, 108208,
                113301, 113302, 113303, 113304, 113305, 113306, 113307, 113308
            };
            sim_get_next(new Sim_type_p(tags), e);

            if (e.get_tag() != -1) {

                int tag = e.get_tag();
                int mRNAID = tag % 10;

                sim_completed(e);

                double concentrations = (double) MolecularCounter.getTranscriptionProteinConc();

                if (concentrations != 0) {


                    switch (mRNAID) {
                        case 1:
                            mRNA1 = new Sim_negexp_obj("mRNA", (initiation + 470) * concentrations);
                            time = mRNA1.sample();
                            break;
                        case 2:
                            mRNA2 = new Sim_negexp_obj("mRNA", (initiation + 450) * concentrations);
                            time = mRNA2.sample();
                            break;
                        case 3:
                            mRNA3 = new Sim_negexp_obj("mRNA", (initiation + 360) * concentrations);
                            time = mRNA3.sample();
                            break;
                        case 4:
                            mRNA4 = new Sim_negexp_obj("mRNA", (initiation + 360) * concentrations);
                            time = mRNA4.sample();
                            break;
                        case 5:
                            mRNA5 = new Sim_negexp_obj("mRNA", (initiation + 310) * concentrations);
                            time = mRNA5.sample();
                            break;
                        case 6:
                            mRNA6 = new Sim_negexp_obj("mRNA", (initiation + 280) * concentrations);
                            time = mRNA6.sample();
                            break;
                        case 7:
                            mRNA7 = new Sim_negexp_obj("mRNA", (initiation + 210) * concentrations);
                            time = mRNA7.sample();
                            break;
                        case 8:
                            mRNA8 = new Sim_negexp_obj("mRNA", (initiation + 180) * concentrations);
                            time = mRNA8.sample();
                            break;
                    }


                    sim_schedule(to_Viral_protein_translation, time, (110100 + mRNAID));
                    MolecularCounter.AddmRNACount(mRNAID, poly[mRNAID - 1], Sim_system.sim_clock());

                    MolecularCounter.removePrecursormRNA(poly[mRNAID - 1]);
                    System.out.println("mRNA transcription: " + tag + " at " + Sim_system.sim_clock());

                } else {
                    time = 0.25;
                }

                if (prob.sample() < 0.0005) {
                    //Degradation
                    MolecularCounter.RemovemRNACount(mRNAID, poly[mRNAID - 1], Sim_system.sim_clock());
                }

                sim_schedule(to_mRNA_transcription, time, e.get_tag());
            }
        }
    }
}

class Viral_protein_translation extends Sim_entity {

    private Sim_port in;
    private Sim_port to_Protein_secretion;
    private Sim_port to_M1_translocation;
    private Sim_port to_Viral_protein_import;
    private Sim_port to_RNP_nuclear_export;
    private Sim_port to_Viral_protein_translation;
    private Sim_negexp_obj trans1;
    private Sim_negexp_obj trans2;
    private Sim_negexp_obj trans3;
    private Sim_negexp_obj trans4;
    private Sim_negexp_obj trans5;
    private Sim_negexp_obj trans6;
    private Sim_negexp_obj trans7;
    private Sim_negexp_obj trans8;
    private Sim_negexp_obj trans9;
    private Sim_negexp_obj trans10;
    private Sim_random_obj prob;
    private int[] ribo = new int[]{29, 29, 27, 22, 19, 17, 12, 11, 11, 11};
    double time1;
    double time2;
    double initiation = 500.0;

    Viral_protein_translation(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);

        to_Protein_secretion = new Sim_port("to_Protein_secretion");
        add_port(to_Protein_secretion);
        to_M1_translocation = new Sim_port("to_M1_translocation");
        add_port(to_M1_translocation);
        to_Viral_protein_import = new Sim_port("to_Viral_protein_import");
        add_port(to_Viral_protein_import);
        to_RNP_nuclear_export = new Sim_port("to_RNP_nuclear_export");
        add_port(to_RNP_nuclear_export);
        to_Viral_protein_translation = new Sim_port("to_Viral_protein_translation");
        add_port(to_Viral_protein_translation);

        trans1 = new Sim_negexp_obj("Translate", initiation + 42.0);
        add_generator(trans1);
        trans2 = new Sim_negexp_obj("Translate", initiation + 42.0);
        add_generator(trans2);
        trans3 = new Sim_negexp_obj("Translate", initiation + 40.0);
        add_generator(trans3);
        trans4 = new Sim_negexp_obj("Translate", initiation + 31.0);
        add_generator(trans4);
        trans5 = new Sim_negexp_obj("Translate", initiation + 28.0);
        add_generator(trans5);
        trans6 = new Sim_negexp_obj("Translate", initiation + 25.0);
        add_generator(trans6);
        trans7 = new Sim_negexp_obj("Translate", initiation + 14.0);
        add_generator(trans7);
        trans8 = new Sim_negexp_obj("Translate", initiation + 5.0);
        add_generator(trans8);
        trans9 = new Sim_negexp_obj("Translate", initiation + 13.0);
        add_generator(trans9);
        trans10 = new Sim_negexp_obj("Translate", initiation + 7.0);
        add_generator(trans10);

        prob = new Sim_random_obj("Probability");
        add_generator(prob);

    }

    @Override
    public void body() {
        while (Sim_system.running()) {

            Sim_event e = new Sim_event();
            int[] tags = {110101, 110102, 110103, 110104, 110105, 110106, 110107, 110108};
            sim_get_next(new Sim_type_p(tags), e);

            if (e.get_tag() != -1) {

                sim_completed(e);

                int tag = e.get_tag();
                int transID = tag % 10;

                double concentrations = MolecularCounter.getTranscriptConc(transID);

                switch (transID) {
                    case 1:
                        trans1 = new Sim_negexp_obj("Translate", initiation + 420.0 * concentrations);
                        time1 = trans1.sample();
                        break;
                    case 2:
                        trans2 = new Sim_negexp_obj("Translate", initiation + 420.0 * concentrations);
                        time1 = trans2.sample();
                        break;
                    case 3:
                        trans3 = new Sim_negexp_obj("Translate", initiation + 400.0 * concentrations);
                        time1 = trans3.sample();
                        break;
                    case 4:
                        trans4 = new Sim_negexp_obj("Translate", initiation + 310.0 * concentrations);
                        time1 = trans4.sample();
                        break;
                    case 5:
                        trans5 = new Sim_negexp_obj("Translate", initiation + 280.0 * concentrations);
                        time1 = trans5.sample();
                        break;
                    case 6:
                        trans6 = new Sim_negexp_obj("Translate", initiation + 250.0 * concentrations);
                        time1 = trans6.sample();
                        break;
                    case 7:
                        trans7 = new Sim_negexp_obj("Translate", initiation + 140.0 * concentrations);
                        time1 = trans7.sample();
                        break;
                    case 8:
                        trans8 = new Sim_negexp_obj("Translate", initiation + 130.0 * concentrations);
                        time1 = trans9.sample(); //Segment 8 really code for protein 9 in this case
                        break;
                }

                double clock = Sim_system.sim_clock();

                switch (transID) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        sim_schedule(to_Viral_protein_import, time1, 111300 + transID);
                        MolecularCounter.AddProteinCount(transID, ribo[transID - 1], clock);
                        break;

                    case 7:
                        if (prob.sample() < 0.5) {
                            sim_schedule(to_M1_translocation, time1, 111207);
                        } else {
                            sim_schedule(to_Viral_protein_import, time1, 111307);
                        }
                        MolecularCounter.AddProteinCount(7, ribo[transID - 1], clock);
                        if (prob.sample() < 0.1) {
                            time2 = trans8.sample();
                            sim_schedule(to_Protein_secretion, time2, 111108);
                            MolecularCounter.AddProteinCount(8, ribo[transID], clock);
                        }
                        break;

                    case 8:
                        sim_schedule(to_Viral_protein_import, time1, 111309);
                        MolecularCounter.AddProteinCount(9, ribo[transID], clock);
                        if (prob.sample() < 0.1) {
                            time2 = trans10.sample();
                            sim_schedule(to_Viral_protein_import, time2, 111310);
                            MolecularCounter.AddProteinCount(10, ribo[transID + 1], clock);
                        }
                        break;
                }
                System.out.println("Translation: time: " + time1 + " at " + clock);

                sim_schedule(to_Viral_protein_translation, time1, e.get_tag());
            }
        }
    }
}

class Viral_protein_import extends Sim_entity {

    private Sim_port in;
    private Sim_port to_mRNA_transcription;
    private Sim_port to_cRNA_synthesis;
    private Sim_port to_vRNA_replication;
    private Sim_port to_RNP_nuclear_export;
    private Sim_negexp_obj proc;
    private Sim_random_obj prob;

    Viral_protein_import(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);

        to_mRNA_transcription = new Sim_port("to_mRNA_transcription");
        add_port(to_mRNA_transcription);
        to_cRNA_synthesis = new Sim_port("to_cRNA_synthesis");
        add_port(to_cRNA_synthesis);
        to_vRNA_replication = new Sim_port("to_vRNA_replication");
        add_port(to_vRNA_replication);
        to_RNP_nuclear_export = new Sim_port("to_RNP_nuclear_export");
        add_port(to_RNP_nuclear_export);

        proc = new Sim_negexp_obj("Processing", 10.0);
        add_generator(proc);

        prob = new Sim_random_obj("Probability");
        add_generator(prob);

    }

    @Override
    public void body() {

        while (Sim_system.running()) {

            Sim_event e = new Sim_event();
            int[] tags = {111301, 111302, 111303, 111304, 111305, 111306, 111307, 111308, 111309, 111310};//
            sim_get_next(new Sim_type_p(tags), e);

            if (e.get_tag() != -1) {

                double cRNAprob = 0;
                double vRNAprob = 0;
                double vRNPExportprob = 0;

                sim_completed(e);

                int tag = e.get_tag();
                int proteinID = tag % 100;
                System.out.println("Protein Import: " + tag + " at " + Sim_system.sim_clock());


                switch (proteinID) {
                    case 8:
                        cRNAprob = 0.0;
                        vRNAprob = 1.0;
                        //vRNPExportprob = 1.0;
                        break;
                    case 10:
                        cRNAprob = 0.0;
                        vRNAprob = 0.0;
                        vRNPExportprob = 1.0;
                        break;
                    default:
                        cRNAprob = 0.5;
                        vRNAprob = 1.0;
                        //vRNPExportprob = 1.0;
                        break;
                }

                double p = prob.sample();
                if (p <= cRNAprob) {
                    sim_schedule(to_cRNA_synthesis, proc.sample(), 112100 + proteinID);
                } else if (p <= vRNAprob) {
                    sim_schedule(to_vRNA_replication, proc.sample(), 112200 + proteinID);
                } else if (p <= vRNPExportprob) {
                    sim_schedule(to_RNP_nuclear_export, proc.sample(), 112300 + proteinID);
                }
            //sim_trace(1, "Viral protein import: " + e.get_src() + " from " + tag + " at " + Sim_system.sim_clock());
            }
        }
    }
}

class vRNA_replication extends Sim_entity {

    private Sim_port in;
    private Sim_port to_cRNA_synthesis;
    private Sim_port to_RNP_assembly;
    private Sim_port to_vRNA_replication;
    private Sim_port to_mRNA_transcription;
    private Sim_negexp_obj vRNA1;
    private Sim_negexp_obj vRNA2;
    private Sim_negexp_obj vRNA3;
    private Sim_negexp_obj vRNA4;
    private Sim_negexp_obj vRNA5;
    private Sim_negexp_obj vRNA6;
    private Sim_negexp_obj vRNA7;
    private Sim_negexp_obj vRNA8;
    private Sim_negexp_obj vRNP;
    private Sim_random_obj prob;
    private static ArrayList vRNParrived = new ArrayList();
    private int[] poly = new int[]{29, 29, 27, 22, 19, 17, 12, 11};
    double time = 0;
    double initiation = 46.0;

    vRNA_replication(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);
        to_cRNA_synthesis = new Sim_port("to_cRNA_synthesis");
        add_port(to_cRNA_synthesis);
        to_RNP_assembly = new Sim_port("to_RNP_assembly");
        add_port(to_RNP_assembly);
        to_vRNA_replication = new Sim_port("to_vRNA_replication");
        add_port(to_vRNA_replication);
        to_mRNA_transcription = new Sim_port("to_mRNA_transcription");
        add_port(to_mRNA_transcription);


        vRNA1 = new Sim_negexp_obj("vRNA", initiation + 47);
        add_generator(vRNA1);
        vRNA2 = new Sim_negexp_obj("vRNA", initiation + 47);
        add_generator(vRNA2);
        vRNA3 = new Sim_negexp_obj("vRNA", initiation + 45);
        add_generator(vRNA3);
        vRNA4 = new Sim_negexp_obj("vRNA", initiation + 36);
        add_generator(vRNA4);
        vRNA5 = new Sim_negexp_obj("vRNA", initiation + 31);
        add_generator(vRNA5);
        vRNA6 = new Sim_negexp_obj("vRNA", initiation + 28);
        add_generator(vRNA6);
        vRNA7 = new Sim_negexp_obj("vRNA", initiation + 21);
        add_generator(vRNA7);
        vRNA8 = new Sim_negexp_obj("vRNA", initiation + 18);
        add_generator(vRNA8);


        vRNP = new Sim_negexp_obj("vRNP", 6.0);
        add_generator(vRNP);

        prob = new Sim_random_obj("Probability");
        add_generator(prob);

    }

    @Override
    public void body() {
        while (Sim_system.running()) {

            Sim_event e = new Sim_event();
            int[] tags = {
                109101, 109102, 109103, 109104, 109105, 109106, 109107, 109108,
                112201, 112202, 112203, 112204, 112205, 112206, 112207, 112210
            };
            sim_get_next(new Sim_type_p(tags), e);

            if (e.get_tag() != -1) {

                int tag = e.get_tag();

                int vRNAID = tag % 10;
                boolean ready = false;

                sim_completed(e);

                if (((tag >= 109101) && (tag <= 109108))) {
                    ready = MolecularCounter.checkReplicationProteinCount(vRNAID);
                }

                double concentrations = MolecularCounter.getSynthProteinConc();

                switch (vRNAID) {
                    case 1:
                        vRNA1 = new Sim_negexp_obj("vRNA", (initiation + 47) * concentrations);
                        time = vRNA1.sample();
                        break;
                    case 2:
                        vRNA2 = new Sim_negexp_obj("vRNA", (initiation + 47) * concentrations);
                        time = vRNA2.sample();
                        break;
                    case 3:
                        vRNA3 = new Sim_negexp_obj("vRNA", (initiation + 47) * concentrations);
                        time = vRNA3.sample();
                        break;
                    case 4:
                        vRNA4 = new Sim_negexp_obj("vRNA", (initiation + 45) * concentrations);
                        time = vRNA4.sample();
                        break;
                    case 5:
                        vRNA5 = new Sim_negexp_obj("vRNA", (initiation + 38) * concentrations);
                        time = vRNA5.sample();
                        break;
                    case 6:
                        vRNA6 = new Sim_negexp_obj("vRNA", (initiation + 28) * concentrations);
                        time = vRNA6.sample();
                        break;
                    case 7:
                        vRNA7 = new Sim_negexp_obj("vRNA", (initiation + 21) * concentrations);
                        time = vRNA7.sample();
                        break;
                    case 8:
                        vRNA8 = new Sim_negexp_obj("vRNA", (initiation + 18) * concentrations);
                        time = vRNA8.sample(); //Segment 8 really code for protein 9 in this case
                        break;
                    }

                if (ready) {

                    double p = prob.sample();
                    if (p < 0.06) {
                        sim_schedule(to_cRNA_synthesis, time, 113100 + vRNAID);
                    } else {
                        sim_schedule(to_mRNA_transcription, time, 113300 + vRNAID);
                    }
                    MolecularCounter.AddvRNACount(vRNAID, poly[vRNAID - 1], Sim_system.sim_clock());

                    System.out.println("vRNA replication: " + tag + " at " + Sim_system.sim_clock());

                    //sim_trace(1, "vRNA synthesis: " + e.get_src() + " from " + tag + " at " + Sim_system.sim_clock());

                    vRNParrived.add(vRNAID);
                }
                if (tag == 112207) {
                    int length = vRNParrived.size();
                    if (length > 0) {

                        double temp = prob.sample() * length;
                        int index = (int) Math.ceil(temp - 1);
                        vRNAID = (Integer) vRNParrived.get(index);

                        vRNA7 = new Sim_negexp_obj("vRNA", (initiation + 21) * concentrations);
                        time = vRNA7.sample();

                        sim_schedule(to_RNP_assembly, time, 113200 + vRNAID);

                        vRNParrived.remove(index);
                    }
                }
                sim_schedule(to_vRNA_replication, time, e.get_tag());
            }
        }
    }
    }

class RNP_assembly extends Sim_entity {

    private Sim_port in;
    private Sim_port to_RNP_nuclear_export;
    private Sim_negexp_obj proc;
    private Sim_random_obj prob;

    RNP_assembly(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);
        to_RNP_nuclear_export = new Sim_port("to_RNP_nuclear_export");
        add_port(to_RNP_nuclear_export);

        proc = new Sim_negexp_obj("Processing", 10.0);
        add_generator(proc);

        prob = new Sim_random_obj("Probability");
        add_generator(prob);

    }

    @Override
    public void body() {
        while (Sim_system.running()) {
            Sim_event e = new Sim_event();
            int[] tags = {113201, 113202, 113203, 113204, 113205, 113206, 113207, 113208};
            sim_get_next(new Sim_type_p(tags), e);

            if (e.get_tag() != -1) {

                sim_completed(e);

                int tag = e.get_tag();
                int vRNPID = tag % 10;
                System.out.println("RNP assmebly: " + tag + " at " + Sim_system.sim_clock());

                sim_schedule(to_RNP_nuclear_export, proc.sample(), 114100 + vRNPID);
                MolecularCounter.AddvRNPCount(vRNPID, 1, Sim_system.sim_clock());
            //sim_trace(1, "RNP assembly: " + e.get_src() + " from " + tag + " at " + Sim_system.sim_clock());
            }
        }
    }
    }

class RNP_nuclear_export extends Sim_entity {

    private Sim_port in;
    private Sim_port to_Virion_assembly;
    private Sim_negexp_obj proc;
    private Sim_random_obj prob;
    private Sim_stat stat;

    RNP_nuclear_export(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);
        to_Virion_assembly = new Sim_port("to_Virion_assembly");
        add_port(to_Virion_assembly);

        proc = new Sim_negexp_obj("Processing", 600.0);
        add_generator(proc);

        prob = new Sim_random_obj("Probability");
        add_generator(prob);

    }

    @Override
    public void body() {
        while (Sim_system.running()) {
            Sim_event e = new Sim_event();
            int[] tags = {
                //111101, 111102, 111103, 111104, 111105, 111106, 111107, 111108,
                112307, 112310, //M1, NS2/NEP
                114101, 114102, 114103, 114104, 114105, 114106, 114107, 114108
            };
            sim_get_next(new Sim_type_p(tags), e);

            if (e.get_tag() != -1) {

                sim_completed(e);

                if ((e.get_tag() >= 114101) && (e.get_tag() <= 114108)) {

                    int tag = e.get_tag();
                    int vRNPID = tag % 10;

                    sim_schedule(to_Virion_assembly, proc.sample(), (115100 + vRNPID));

                    System.out.println("RNP assmebly: " + tag + " at " + Sim_system.sim_clock());

                //sim_trace(1, "RNP export: " + e.get_src() + " from " + tag + " at " + Sim_system.sim_clock());
                }
            }
        }
    }
    }

class M1_translocation extends Sim_entity {

    private Sim_port in;
    private Sim_port to_Virion_assembly;
    private Sim_negexp_obj proc;
    private Sim_random_obj prob;

    M1_translocation(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);
        to_Virion_assembly = new Sim_port("to_Virion_assembly");
        add_port(to_Virion_assembly);

        proc = new Sim_negexp_obj("Processing", 1.0);
        add_generator(proc);

        prob = new Sim_random_obj("Probability");
        add_generator(prob);
    }

    @Override
    public void body() {
        while (Sim_system.running()) {
            Sim_event e = new Sim_event();
            int[] tags = {111207};
            sim_get_next(new Sim_type_p(tags), e);

            if (e.get_tag() != -1) {

                sim_completed(e);

                sim_schedule(to_Virion_assembly, proc.sample(), 116107);
            //sim_trace(1, "M1 translocation: " + e.get_src() + " from " + e.get_tag() + " at " + Sim_system.sim_clock());
            }
        }
    }
    }

class Protein_secretion extends Sim_entity {

    private Sim_port in;
    private Sim_port to_Virion_assembly;
    private Sim_negexp_obj proc;
    private Sim_random_obj prob;

    Protein_secretion(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);
        to_Virion_assembly = new Sim_port("to_Virion_assembly");
        add_port(to_Virion_assembly);

        proc = new Sim_negexp_obj("Processing", 1.0);
        add_generator(proc);

        prob = new Sim_random_obj("Probability");
        add_generator(prob);
    }

    @Override
    public void body() {

        while (Sim_system.running()) {

            Sim_event e = new Sim_event();
            int[] tags = {111104, 111106, 111108};
            sim_get_next(new Sim_type_p(tags), e);

            if (e.get_tag() != -1) {

                sim_completed(e);

                switch (e.get_tag()) {
                    case 111104:
                        sim_schedule(to_Virion_assembly, proc.sample(), 117104);
                        break;
                    case 111106:
                        sim_schedule(to_Virion_assembly, proc.sample(), 117106);
                        break;
                    case 111108:
                        sim_schedule(to_Virion_assembly, proc.sample(), 117108);
                        break;
                    }
            //sim_trace(1, "Protein secretion: " + e.get_src() + " from " + e.get_tag() + " at " + Sim_system.sim_clock());
            }
        }
    }
    }

class Virion_assembly extends Sim_entity {

    private Sim_port in;
    private Sim_port to_Virion_release;
    private Sim_negexp_obj proc;
    private Sim_random_obj prob;

    Virion_assembly(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);
        to_Virion_release = new Sim_port("to_Virion_release");
        add_port(to_Virion_release);

        proc = new Sim_negexp_obj("proc", 10.0);
        add_generator(proc);

        prob = new Sim_random_obj("Probability");
        add_generator(prob);
    }

    @Override
    public void body() {
        boolean ready = false;

        while (Sim_system.running()) {

            Sim_event e = new Sim_event();

            int[] tags = {
                115101, 115102, 115103, 115104, 115105, 115106, 115107, 115108,
                116107,
                117104, 117106, 117108
            };
            sim_get_next(new Sim_type_p(tags), e);

            sim_completed(e);

            if ((e.get_tag() >= 115101) && (e.get_tag() <= 115108)) {

                ready = MolecularCounter.checkVirionvRNPCount();

                if (ready) {
                    ready = MolecularCounter.checkVirionProteinCount();
                }

                if (ready) {

                    sim_schedule(to_Virion_release, proc.sample(), 118101);

                    //sim_trace(1, "Virion Assembly: " + e.get_tag()  + " at " + Sim_system.sim_clock());

                    System.out.println("Virion Assembly: " + e.get_tag() + " at " + Sim_system.sim_clock());
                }
            }
        }
    }
    }

class Virion_release extends Sim_entity {

    private Sim_port in;
    private Sim_negexp_obj proc;
    private Sim_random_obj prob;

    Virion_release(String name) {
        super(name);

        in = new Sim_port("In");
        add_port(in);

        proc = new Sim_negexp_obj("Processing", 1.0);
        add_generator(proc);

        prob = new Sim_random_obj("Probability");
        add_generator(prob);

    }

    @Override
    public void body() {
        while (Sim_system.running()) {
            Sim_event e = new Sim_event();
            int[] tags = {118101};
            sim_get_next(new Sim_type_p(tags), e);

            if (e.get_tag() != -1) {

                sim_completed(e);

                MolecularCounter.AddVirionCount(1, Sim_system.sim_clock());

            //sim_trace(1, "Virion release: " + e.get_src() + " from " + tag + " at " + Sim_system.sim_clock());
            }
        }
    }
    }

