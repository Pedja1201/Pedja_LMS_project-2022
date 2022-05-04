import { Nastavnik } from "./nastavnik";
import { TipNastave } from "./tip-nastave";

export interface NastavnikNaRealizaciji {
    id:number;
    brojCasova:number;
    nastavnik:Nastavnik[];
    tipNastave:TipNastave[]
}
