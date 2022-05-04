import { GodinaStudija } from "./godina-studija";

export interface StudentNaGodini {
    id:number;
    datumUpisa:Date;
    brojIndeksa:String;
    godinaStudija:GodinaStudija[]
}
