import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MottosComponent } from './mottos.component';

describe('MottosComponent', () => {
  let component: MottosComponent;
  let fixture: ComponentFixture<MottosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MottosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MottosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
