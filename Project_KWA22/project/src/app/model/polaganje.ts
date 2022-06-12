import { EvaluacijaZnanja } from "./evaluacija-znanja";
import { StudentNaGodini } from "./student-na-godini";

export interface Polaganje {
    id:number;
    bodovi:number;
    napomena:string;
    evaluacijaZnanja:EvaluacijaZnanja[];
    studentNaGodini:StudentNaGodini[];
}
