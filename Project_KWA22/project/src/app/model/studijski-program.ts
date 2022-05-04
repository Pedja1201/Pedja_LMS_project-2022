import { Fakultet } from "./fakultet";
import { GodinaStudija } from "./godina-studija";
import { Nastavnik } from "./nastavnik";

export interface StudijskiProgram {
    id:number;
    naziv:String;
    fakultet:Fakultet[];
    nastavnik:Nastavnik[];
    godinaStudija:GodinaStudija[]
}
