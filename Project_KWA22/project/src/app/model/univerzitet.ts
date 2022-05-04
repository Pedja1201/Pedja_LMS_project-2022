import { Adresa } from "./adresa";
import { Nastavnik } from "./nastavnik";

export interface Univerzitet {
    id:number;
    naziv:String;
    datumVremeOsnivanja:Date;
    adresa:Adresa[];
    nastavnik:Nastavnik[]
}
