import { Adresa } from "./adresa";
import { PohadjanjePredmeta } from "./pohadjanje-predmeta";
import { StudentNaGodini } from "./student-na-godini";

export interface Student {
    id:number;
    jmbg:String;
    ime:String;
    adresa:Adresa[];
    pohadjanjePredmeta:PohadjanjePredmeta[];
    studentNaGodini:StudentNaGodini[]
}
