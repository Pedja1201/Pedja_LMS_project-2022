import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdreseComponent } from './page/adrese/adrese.component';
import { DetailsAdreseComponent } from './page/adrese/details-adrese/details-adrese.component';
import { DetailsDrzavaComponent } from './page/drzave/details-drzava/details-drzava.component';
import { DrzaveComponent } from './page/drzave/drzave.component';
import { DetailsFakultetaComponent } from './page/fakulteti/details-fakulteta/details-fakulteta.component';
import { FakultetiComponent } from './page/fakulteti/fakulteti.component';
import { DetailsGodineStudijaComponent } from './page/godine-studija/details-godine-studija/details-godine-studija.component';
import { GodineStudijaComponent } from './page/godine-studija/godine-studija.component';
import { AuthGuard } from './guards/auth.guard';
import { DetailsIshodaComponent } from './page/ishodi/details-ishoda/details-ishoda.component';
import { IshodiComponent } from './page/ishodi/ishodi.component';
import { LoginComponent } from './login/login.component';
import { DetailsMestaComponent } from './page/mesta/details-mesta/details-mesta.component';
import { MestaComponent } from './page/mesta/mesta.component';
import { DetailsNastavnikaNaRealizacijiComponent } from './page/nastavnici-na-realizaciji/details-nastavnika-na-realizaciji/details-nastavnika-na-realizaciji.component';
import { NastavniciNaRealizacijiComponent } from './page/nastavnici-na-realizaciji/nastavnici-na-realizaciji.component';
import { DetailsNastavnikaComponent } from './page/nastavnici/details-nastavnika/details-nastavnika.component';
import { NastavniciComponent } from './page/nastavnici/nastavnici.component';
import { DetailsNaucneOblastiComponent } from './page/naucne-oblasti/details-naucne-oblasti/details-naucne-oblasti.component';
import { NaucneOblastiComponent } from './page/naucne-oblasti/naucne-oblasti.component';
import { NotFoundComponent } from './page/not-found/not-found.component';
import { DetailsPohadjanjaPredmetaComponent } from './page/pohadjanja-predmeta/details-pohadjanja-predmeta/details-pohadjanja-predmeta.component';
import { PohadjanjaPredmetaComponent } from './page/pohadjanja-predmeta/pohadjanja-predmeta.component';
import { DetailsPredmetaComponent } from './page/predmeti/details-predmeta/details-predmeta.component';
import { PredmetiComponent } from './page/predmeti/predmeti.component';
import { DetailsRealizacijePredmetaComponent } from './page/realizacije-predmeta/details-realizacije-predmeta/details-realizacije-predmeta.component';
import { RealizacijePredmetaComponent } from './page/realizacije-predmeta/realizacije-predmeta.component';
import { DetailsStudentaNaGodiniComponent } from './page/studenti-na-godini/details-studenta-na-godini/details-studenta-na-godini.component';
import { StudentiNaGodiniComponent } from './page/studenti-na-godini/studenti-na-godini.component';
import { DetailsStudentaComponent } from './page/studenti/details-studenta/details-studenta.component';
import { StudentiComponent } from './page/studenti/studenti.component';
import { DetailsStudijskihProgramaComponent } from './page/studijski-programi/details-studijskih-programa/details-studijskih-programa.component';
import { StudijskiProgramiComponent } from './page/studijski-programi/studijski-programi.component';
import { DetailsTipaNastaveComponent } from './page/tipovi-nastave/details-tipa-nastave/details-tipa-nastave.component';
import { TipoviNastaveComponent } from './page/tipovi-nastave/tipovi-nastave.component';
import { DetailsTipaZvanjaComponent } from './page/tipovi-zvanja/details-tipa-zvanja/details-tipa-zvanja.component';
import { TipoviZvanjaComponent } from './page/tipovi-zvanja/tipovi-zvanja.component';
import { DetailsUniverzitetaComponent } from './page/univerziteti/details-univerziteta/details-univerziteta.component';
import { UniverzitetiComponent } from './page/univerziteti/univerziteti.component';
import { WelcomeComponent } from './page/welcome/welcome.component';
import { DetailsZvanjaComponent } from './page/zvanja/details-zvanja/details-zvanja.component';
import { ZvanjaComponent } from './page/zvanja/zvanja.component';
import { TreeViewComponent } from './tree-view/tree-view.component';
import { UsersComponent } from './page/users/users.component';
import { DetailsUsersComponent } from './page/users/details-users/details-users.component';

const routes: Routes = [
  {path: "", component: WelcomeComponent},
  {path:"", redirectTo: "", pathMatch:"full"}, ///Vraca na korensku rutu

  //Users
  {path: 'users',component: UsersComponent,
      data: { allowedRoles: ['ROLE_ADMIN']}, canActivate: [AuthGuard]}, //Login pre otvaranja
  {path: 'users/:id',component: DetailsUsersComponent},
  //Adrese
  {path: "adrese", component: AdreseComponent, 
        data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]}, //Login pre otvaranja
  {path: "adrese/:id", component: DetailsAdreseComponent},

  //drzave
  {path: 'drzave', component: DrzaveComponent, 
        data: {allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "drzave/:id", component: DetailsDrzavaComponent},

  //fakulteti
  {path: 'fakulteti', component: FakultetiComponent, 
        data: {allowedRoles: ['ROLE_ADMIN']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "fakulteti/:id", component: DetailsFakultetaComponent},

  //godineStudija
  {path: 'godineStudija', component: GodineStudijaComponent, 
        data: {allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "godineStudija/:id", component: DetailsGodineStudijaComponent},

  //ishodi
  {path: 'ishodi', component: IshodiComponent, 
        data: {allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "ishodi/:id", component: DetailsIshodaComponent},

  //mesta
  {path: 'mesta', component: MestaComponent, 
        data: {allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "mesta/:id", component: DetailsMestaComponent},

  //nastavnici
  {path: 'nastavnici', component: NastavniciComponent, 
        data: {allowedRoles: ['ROLE_ADMIN']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "nastavnici/:id", component: DetailsNastavnikaComponent},

  //nastavniciNaRealizaciji
  {path: 'nastavniciNaRealizaciji', component: NastavniciNaRealizacijiComponent, 
        data: {allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "nastavniciNaRealizaciji/:id", component: DetailsNastavnikaNaRealizacijiComponent},

  //naucneOblasti
  {path: 'naucneOblasti', component: NaucneOblastiComponent, 
        data: {allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "naucneOblasti/:id", component: DetailsNaucneOblastiComponent},

  //pohadjanjaPredmeta
  {path: 'pohadjanjaPredmeta', component: PohadjanjaPredmetaComponent, 
        data: {allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "pohadjanjaPredmeta/:id", component: DetailsPohadjanjaPredmetaComponent},

  //predmeti
  {path: 'predmeti', component: PredmetiComponent, 
        data: {allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "predmeti/:id", component: DetailsPredmetaComponent},

  //realizacijePredmeta
  {path: 'realizacijePredmeta', component: RealizacijePredmetaComponent, 
        data: {allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "realizacijePredmeta/:id", component: DetailsRealizacijePredmetaComponent},

  //studenti
  {path: 'studenti', component: StudentiComponent, 
        data: {allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "studenti/:id", component: DetailsStudentaComponent},

  //studentiNaGodini
  {path: 'studentiNaGodini', component: StudentiNaGodiniComponent, 
        data: {allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "studentiNaGodini/:id", component: DetailsStudentaNaGodiniComponent},

  //studijskiProgrami
  {path: 'studijskiProgrami', component: StudijskiProgramiComponent, 
        data: {allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "studijskiProgrami/:id", component: DetailsStudijskihProgramaComponent},

  //tipoviNastave
  {path: 'tipoviNastave', component: TipoviNastaveComponent, 
        data: {allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "tipoviNastave/:id", component: DetailsTipaNastaveComponent},

  //tipoviZvanja
  {path: 'tipoviZvanja', component: TipoviZvanjaComponent, 
        data: {allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "tipoviZvanja/:id", component: DetailsTipaZvanjaComponent},

  //univerziteti
  {path: 'univerziteti', component: UniverzitetiComponent, 
        data: {allowedRoles: ['ROLE_ADMIN']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "univerziteti/:id", component: DetailsUniverzitetaComponent},

  //zvanja
  {path: 'zvanja', component: ZvanjaComponent, 
        data: {allowedRoles: ['ROLE_ADMIN', 'ROLE_USER']}, canActivate: [AuthGuard]},//Login pre otvaranja
  {path: "zvanja/:id", component: DetailsZvanjaComponent},

  //Stablo
  {path:"tree", component:TreeViewComponent},

  //Login
  {path:"login", component:LoginComponent},
  
  //Prazna stranica
  {path: '**', component: NotFoundComponent,},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
