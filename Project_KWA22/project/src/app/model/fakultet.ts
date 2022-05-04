import { Adresa } from "./adresa";
import { Nastavnik } from "./nastavnik";
import { Univerzitet } from "./univerzitet";

export interface Fakultet {
    id:number;
    naziv:String;
    univerzitet:Univerzitet[];
    adresa:Adresa[];
    nastavnik:Nastavnik[]
}
