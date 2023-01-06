package com.epam.rd.autotasks.segments;

import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {
    Point start;
    Point end;
    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
        if ((start.getX() == end.getX()) &&
           (start.getY() == end.getY())){
                throw new IllegalArgumentException();
        }
    }

    double length() {
        return sqrt(
               pow(end.getX() - start.getX(), 2) +
               pow(end.getY() - start.getY(), 2) );
    }

    double length(Point start, Point end) {
        return sqrt(
               pow(end.getX() - start.getX(), 2) +
               pow(end.getY() - start.getY(), 2) );
    }
    Point middle() {
        return new Point((start.getX() + end.getX()) / 2,
                         (start.getY() + end.getY()) / 2 );
    }
    Point intersection(Segment another) {
        final double a;
        final double b;
        final double c;
        a = start.getX() * end.getY() - start.getY() * end.getX();
        b = another.start.getX() * another.end.getY() - another.start.getY() * another.start.getX();
        c = (start.getX() - end.getX()) * (another.start.getY() - another.end.getY()) -
                (start.getY() - end.getY()) * (another.start.getX() - another.end.getX());
        Point result = new Point((a * (another.start.getX() - another.end.getX()) - b * (start.getX() - end.getX())) / c,
                (a * (another.start.getY() - another.end.getY()) - b * (start.getY() - end.getY())) / c);

        if ((c != 0) && (Math.abs(this.length() - (length(this.start, result) + length(this.end, result))) < 1e-9)
                && (Math.abs(another.length() - (length(another.start, result) + length(another.end, result))) < 1e-9)) {
            return result;
        } else {return null;}
    }
}
