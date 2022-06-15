import { CdkDragDrop, CdkDragEnd, CdkDropList, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { RealizacijaPredmeta, RealizacijaPredmetaPage } from 'src/app/model/realizacija-predmeta';
import { RealizacijePredmetaService } from 'src/app/service/realizacije-predmeta.service';

@Component({
  selector: 'app-tabela-realizacije-predmeta',
  templateUrl: './tabela-realizacije-predmeta.component.html',
  styleUrls: ['./tabela-realizacije-predmeta.component.css']
})
export class TabelaRealizacijePredmetaComponent implements OnInit {
  displayedColumns: string[] = ['id', 'naziv', 'nastavnikNaRealizaciji', 'predmet', 'evaluacijaZnanja','terminNastave', "akcije"];
  dataSource : RealizacijaPredmetaPage<RealizacijaPredmeta> | undefined;
  title="Tabela Realizacije predmeta";

  @Input()
  elementi: any[] = [];

  dropped: any = [{}] //DragAndDrop

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : RealizacijePredmetaService, private router : Router) { 
    servis.getAll().subscribe((realizacijePredmeta : RealizacijaPredmetaPage<RealizacijaPredmeta>) => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = realizacijePredmeta.content;                            //Rutiranja posebne tabele komponenete       
    });
  }

  ngOnInit(): void {
  }

  ukloni(id:number) {
    this.uklanjanje.emit(id);
  }

  izmeni(id:number) {
    this.izmena.emit(id);
  }

   //DragAndDrop metoda
   drop(event:CdkDragDrop<any>){
    if (event.previousContainer === event.container){
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else{
      transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex)
    }
  }

  prikaziDetalje(realizacijaPredmeta: RealizacijaPredmeta) {
    this.router.navigate(["/realizacijePredmeta", realizacijaPredmeta.id]);
  }

}
