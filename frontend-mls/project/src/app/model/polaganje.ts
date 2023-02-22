import { EvaluacijaZnanja } from "./evaluacija-znanja";
import { StudentNaGodini } from "./student-na-godini";
export interface PolaganjePage<Polaganje> {
    content: Polaganje[];
  }
export interface Polaganje {
    id:number;
    bodovi:number;
    napomena:string;
    evaluacijaZnanja:EvaluacijaZnanja[];
    studentNaGodini:StudentNaGodini[];
}
