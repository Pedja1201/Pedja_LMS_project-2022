import { RealizacijaPredmeta } from "./realizacija-predmeta";

export interface PohadjanjePredmeta {
    id:number;
    konacnaOcena:number;
    realizacijaPredmeta:RealizacijaPredmeta[]
}
