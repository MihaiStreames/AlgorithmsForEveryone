# -*- coding: utf-8 -*-


class StructException(Exception):

    """Base class for exceptions in the Struct module."""
    
    def __init__(self, message: str) -> None:
        super().__init__(message)


class BaseUnionFindException(StructException):

    """Base class for exceptions in the Union-Find module."""
    
    def __init__(self, message: str) -> None:
        super().__init__(message)

class BaseGraphException(StructException):

    """Base class for exceptions in the Graph module."""
    
    def __init__(self, message: str) -> None:
        super().__init__(message)

class BaseNodeException(StructException):

    """Base class for exceptions in the Node module."""
    
    def __init__(self, message: str) -> None:
        super().__init__(message)

class BaseStackException(StructException):

    """Base class for exceptions in the Stack module."""
    
    def __init__(self, message: str) -> None:
        super().__init__(message)

class BaseQueueException(StructException):

    """Base class for exceptions in the Queue module."""
    
    def __init__(self, message: str) -> None:
        super().__init__(message)

class BaseRBTException(StructException):

    """Base class for exceptions in the Red-Black Tree module."""
    
    def __init__(self, message: str) -> None:
        super().__init__(message)

