import { NastavnikNaRealizaciji } from "./nastavnik-na-realizaciji";
import { Predmet } from "./predmet";

export interface RealizacijaPredmeta {
    id:number;
    naziv:String;
    nastavnikNaRealizaciji:NastavnikNaRealizaciji[];
    predmet:Predmet[]
}
