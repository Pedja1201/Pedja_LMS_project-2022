import { EvaluacijaZnanja } from "./evaluacija-znanja";
import { NastavnikNaRealizaciji } from "./nastavnik-na-realizaciji";
import { Predmet } from "./predmet";
import { TerminNastave } from "./termin-nastave";

export interface RealizacijaPredmeta {
    id:number;
    naziv:String;
    nastavnikNaRealizaciji:NastavnikNaRealizaciji[];
    predmet:Predmet[];
    evaluacijaZnanja:EvaluacijaZnanja[];
    terminNastave: TerminNastave[]
}
