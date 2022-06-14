import { Adresa } from "./adresa";
import { StudentNaGodini } from "./student-na-godini";

export interface Student {
    id:number;
    jmbg:String;
    ime:String;
    adresa:Adresa[];
    studentNaGodini:StudentNaGodini[]
}
