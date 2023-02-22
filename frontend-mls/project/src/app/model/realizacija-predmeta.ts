import { EvaluacijaZnanja } from "./evaluacija-znanja";
import { NastavnikNaRealizaciji } from "./nastavnik-na-realizaciji";
import { Predmet } from "./predmet";
import { TerminNastave } from "./termin-nastave";

export interface RealizacijaPredmetaPage<RealizacijaPredmeta> {
    content: RealizacijaPredmeta[];
  }
export interface RealizacijaPredmeta {
    id:number;
    naziv:String;
    nastavnikNaRealizaciji:NastavnikNaRealizaciji[];
    predmet:Predmet[];
    evaluacijaZnanja:EvaluacijaZnanja[];
    terminNastave: TerminNastave[]
}
