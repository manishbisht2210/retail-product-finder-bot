import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Page} from 'src/app/models/page';

@Component({
  selector: 'app-stylish-pagination',
  templateUrl: './stylish-pagination.component.html',
  styleUrls: ['./stylish-pagination.component.css']
})
export class StylishPaginationComponent implements OnInit, OnChanges {
  @Input() maxPages: number;
  @Input() currentPage: number;
  @Input() itemsPerPage: number;

  @Output() changePage = new EventEmitter();

  pages: any[] = [];
  pageModel: Page = {
    page: this.currentPage,
    itemsPerPage: this.itemsPerPage
  };

  constructor() {
  }

  ngOnInit() {
    if (this.maxPages) {
      this.createPages();
    }
  }

  setPage(page: number, itemsPerPage: number) {
    if (this.pageModel.page !== page) {
      this.pageModel.page = page;
      this.pageModel.itemsPerPage = itemsPerPage;
      this.changePage.emit(this.pageModel);
    }
  }

  createPages() {
    this.pages = [];
    for (let i = 1; i <= this.maxPages; i++) {
      this.pages.push(i);
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    for (const property in changes) {
      if (property === 'itemsPerPage') {
        this.itemsPerPage = changes[property].currentValue;
        // console.log('itemsPerPage Previous:', changes[property].previousValue);
        // console.log('itemsPerPage Current:', changes[property].currentValue);
        // console.log('itemsPerPage firstChange:', changes[property].firstChange);
      } else if (property === 'maxPages') {
        this.maxPages = changes[property].currentValue;
        // console.log('maxPages Previous:', changes[property].previousValue);
        // console.log('maxPages Current:', changes[property].currentValue);
        // console.log('maxPages firstChange:', changes[property].firstChange);
      } else if (property === 'currentPage') {
        this.currentPage = changes[property].currentValue;
        // console.log('currentPage Previous:', changes[property].previousValue);
        // console.log('currentPage Current:', changes[property].currentValue);
        // console.log('currentPage firstChange:', changes[property].firstChange);
      }
    }
    if (this.maxPages) {
      this.createPages();
    }
  }
}
