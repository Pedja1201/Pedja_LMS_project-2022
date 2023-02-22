import { GodinaStudija } from "./godina-studija";
export interface StudentNaGodiniPage<StudentNaGodini> {
    content: StudentNaGodini[];
  }
export interface StudentNaGodini {
    id:number;
    datumUpisa:Date;
    brojIndeksa:String;
    godinaStudija:GodinaStudija[]
}
