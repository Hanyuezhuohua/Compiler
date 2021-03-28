declare i32 @_str_length(i8* %0)
declare i8* @_str_substring(i8* %0, i32 %1, i32 %2)
declare i32 @_str_parseInt(i8* %0)
declare i32 @_str_ord(i8* %0, i32 %1)
declare i8* @_str_concat(i8* %0, i8* %1)
declare i1 @_str_eq(i8* %0, i8* %1)
declare i1 @_str_ne(i8* %0, i8* %1)
declare i1 @_str_lt(i8* %0, i8* %1)
declare i1 @_str_gt(i8* %0, i8* %1)
declare i1 @_str_le(i8* %0, i8* %1)
declare i1 @_str_ge(i8* %0, i8* %1)
declare void @_gbl_print(i8* %0)
declare void @_gbl_println(i8* %0)
declare void @_gbl_printInt(i32 %0)
declare void @_gbl_printlnInt(i32 %0)
declare i8* @_gbl_getString()
declare i32 @_gbl_getInt()
declare i8* @_gbl_toString(i32 %0)
declare i8* @malloc(i32 %0)
@a = common global i32* zeroinitializer, align 4
@str0 = private unnamed_addr constant [0 x i8] c"", align 1
define void @g_init()

0:
;prev: 
;next: 
	%1 = call i8* @malloc(i32 20)
	%2 = bitcast i8* %1 to i32*
	store i32 4, i32* %2, align 4
	%3 = getelementptr inbounds i32, i32* %2, i32 1
	store i32* %3, i32** @a, align 4
	ret void 
}
define i32 @main()

0:
;prev: 
;next: 24 
	%1 = alloca i32**, align 4
	%2 = alloca i32, align 4
	store void , i32* %2, align 0
	store void , i32*** %1, align 0
	call void @g_init()
	%3 = call i8* @malloc(i32 20)
	%4 = bitcast i8* %3 to i32*
	store i32 4, i32* %4, align 4
	%5 = getelementptr inbounds i32, i32* %4, i32 1
	%6 = bitcast i32* %5 to i32**
	store i32** %6, i32*** %1, align 4
	%7 = load i32**, i32*** %1, align 4
	%8 = getelementptr inbounds i32*, i32** %7, i32 0
	%9 = load i32*, i32** @a, align 4
	store i32* %9, i32** %8, align 4
	%10 = load i32**, i32*** %1, align 4
	%11 = getelementptr inbounds i32*, i32** %10, i32 1
	%12 = load i32*, i32** @a, align 4
	store i32* %12, i32** %11, align 4
	%13 = load i32**, i32*** %1, align 4
	%14 = getelementptr inbounds i32*, i32** %13, i32 2
	%15 = load i32*, i32** @a, align 4
	store i32* %15, i32** %14, align 4
	%16 = load i32**, i32*** %1, align 4
	%17 = getelementptr inbounds i32*, i32** %16, i32 3
	%18 = load i32*, i32** @a, align 4
	store i32* %18, i32** %17, align 4
	%19 = load i32**, i32*** %1, align 4
	%20 = bitcast i32** %19 to i32*
	%21 = getelementptr inbounds i32, i32* %20, i32 -1
	%22 = load i32, i32* %21, align 4
	%23 = call i8* @_gbl_toString(i32 %22)
	call void @_gbl_println(i8* %23)
	store i32 0, i32* %2, align 4
	br label %24
24:
;prev: 0 40 
;next: 32 39 
;iDom: 0
;DomFrontiers: 24 
	%25 = load i32**, i32*** %1, align 4
	%26 = getelementptr inbounds i32*, i32** %25, i32 0
	%27 = load i32*, i32** %26, align 4
	%28 = getelementptr inbounds i32, i32* %27, i32 -1
	%29 = load i32, i32* %28, align 4
	%30 = load i32, i32* %2, align 4
	%31 = icmp slt i32 %30, %29
	br i1 %31, label %32, label %39
32:
;prev: 24 
;next: 40 
;iDom: 24
;DomFrontiers: 24 
	%33 = load i32**, i32*** %1, align 4
	%34 = getelementptr inbounds i32*, i32** %33, i32 0
	%35 = load i32, i32* %2, align 4
	%36 = load i32*, i32** %34, align 4
	%37 = getelementptr inbounds i32, i32* %36, i32 %35
	%38 = call i32 @_gbl_getInt()
	store i32 %38, i32* %37, align 4
	br label %40
39:
;prev: 24 
;next: 43 
;iDom: 24
	store i32 0, i32* %2, align 4
	br label %43
40:
;prev: 32 
;next: 24 
;iDom: 32
;DomFrontiers: 24 
	%41 = load i32, i32* %2, align 4
	%42 = add i32 %41, 1
	store i32 %42, i32* %2, align 4
	br label %24
43:
;prev: 39 61 
;next: 51 59 
;iDom: 39
;DomFrontiers: 43 
	%44 = load i32**, i32*** %1, align 4
	%45 = getelementptr inbounds i32*, i32** %44, i32 1
	%46 = load i32*, i32** %45, align 4
	%47 = getelementptr inbounds i32, i32* %46, i32 -1
	%48 = load i32, i32* %47, align 4
	%49 = load i32, i32* %2, align 4
	%50 = icmp slt i32 %49, %48
	br i1 %50, label %51, label %59
51:
;prev: 43 
;next: 61 
;iDom: 43
;DomFrontiers: 43 
	%52 = load i32**, i32*** %1, align 4
	%53 = getelementptr inbounds i32*, i32** %52, i32 1
	%54 = load i32, i32* %2, align 4
	%55 = load i32*, i32** %53, align 4
	%56 = getelementptr inbounds i32, i32* %55, i32 %54
	%57 = load i32, i32* %56, align 4
	%58 = call i8* @_gbl_toString(i32 %57)
	call void @_gbl_print(i8* %58)
	br label %61
59:
;prev: 43 
;next: 64 
;iDom: 43
	%60 = getelementptr inbounds [0 x i8], [0 x i8]* @str0, i32 0, i32 0
	call void @_gbl_println(i8* %60)
	store i32 0, i32* %2, align 4
	br label %64
61:
;prev: 51 
;next: 43 
;iDom: 51
;DomFrontiers: 43 
	%62 = load i32, i32* %2, align 4
	%63 = add i32 %62, 1
	store i32 %63, i32* %2, align 4
	br label %43
64:
;prev: 59 79 
;next: 72 78 
;iDom: 59
;DomFrontiers: 64 
	%65 = load i32**, i32*** %1, align 4
	%66 = getelementptr inbounds i32*, i32** %65, i32 2
	%67 = load i32*, i32** %66, align 4
	%68 = getelementptr inbounds i32, i32* %67, i32 -1
	%69 = load i32, i32* %68, align 4
	%70 = load i32, i32* %2, align 4
	%71 = icmp slt i32 %70, %69
	br i1 %71, label %72, label %78
72:
;prev: 64 
;next: 79 
;iDom: 64
;DomFrontiers: 64 
	%73 = load i32**, i32*** %1, align 4
	%74 = getelementptr inbounds i32*, i32** %73, i32 2
	%75 = load i32, i32* %2, align 4
	%76 = load i32*, i32** %74, align 4
	%77 = getelementptr inbounds i32, i32* %76, i32 %75
	store i32 0, i32* %77, align 4
	br label %79
78:
;prev: 64 
;next: 82 
;iDom: 64
	store i32 0, i32* %2, align 4
	br label %82
79:
;prev: 72 
;next: 64 
;iDom: 72
;DomFrontiers: 64 
	%80 = load i32, i32* %2, align 4
	%81 = add i32 %80, 1
	store i32 %81, i32* %2, align 4
	br label %64
82:
;prev: 78 99 
;next: 90 98 
;iDom: 78
;DomFrontiers: 82 
	%83 = load i32**, i32*** %1, align 4
	%84 = getelementptr inbounds i32*, i32** %83, i32 3
	%85 = load i32*, i32** %84, align 4
	%86 = getelementptr inbounds i32, i32* %85, i32 -1
	%87 = load i32, i32* %86, align 4
	%88 = load i32, i32* %2, align 4
	%89 = icmp slt i32 %88, %87
	br i1 %89, label %90, label %98
90:
;prev: 82 
;next: 99 
;iDom: 82
;DomFrontiers: 82 
	%91 = load i32**, i32*** %1, align 4
	%92 = getelementptr inbounds i32*, i32** %91, i32 3
	%93 = load i32, i32* %2, align 4
	%94 = load i32*, i32** %92, align 4
	%95 = getelementptr inbounds i32, i32* %94, i32 %93
	%96 = load i32, i32* %95, align 4
	%97 = call i8* @_gbl_toString(i32 %96)
	call void @_gbl_print(i8* %97)
	br label %99
98:
;prev: 82 
;next: 
;iDom: 82
	ret i32 0
99:
;prev: 90 
;next: 82 
;iDom: 90
;DomFrontiers: 82 
	%100 = load i32, i32* %2, align 4
	%101 = add i32 %100, 1
	store i32 %101, i32* %2, align 4
	br label %82
}
