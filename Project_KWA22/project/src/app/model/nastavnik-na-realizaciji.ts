import { Nastavnik } from "./nastavnik";
import { TipNastave } from "./tip-nastave";
export interface NastavnikNaRealizacijiPage<NastavnikNaRealizaciji> {
    content: NastavnikNaRealizaciji[];
  }
export interface NastavnikNaRealizaciji {
    id:number;
    brojCasova:number;
    nastavnik:Nastavnik[];
    tipNastave:TipNastave[]
}
