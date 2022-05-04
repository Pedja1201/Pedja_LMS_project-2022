import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-page-tree-view',
  templateUrl: './page-tree-view.component.html',
  styleUrls: ['./page-tree-view.component.css']
})
export class PageTreeViewComponent implements OnInit {
  @Input()
  data : any = null;

  @Input()
  expandable = false;

  expanded=true;

  constructor() { }

  ngOnInit(): void {
  }

  toggleExpanded(){
    this.expanded = !this.expanded;
  }
}
