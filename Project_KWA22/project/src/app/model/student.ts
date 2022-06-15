import { Adresa } from "./adresa";
import { StudentNaGodini } from "./student-na-godini";
export interface StudentPage<Student> {
    content: Student[];
  }
export interface Student {
    id:number;
    jmbg:String;
    ime:String;
    adresa:Adresa[];
    studentNaGodini:StudentNaGodini[]
}
