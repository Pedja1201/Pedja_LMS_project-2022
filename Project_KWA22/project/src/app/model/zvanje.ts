import { NaucnaOblast } from "./naucna-oblast";
import { TipZvanja } from "./tip-zvanja";

export interface Zvanje {
    id:number;
    datumIzbora:Date;
    datumPrestanka:Date;
    naucnaOblast:NaucnaOblast[];
    tipZvanja:TipZvanja[]
}
